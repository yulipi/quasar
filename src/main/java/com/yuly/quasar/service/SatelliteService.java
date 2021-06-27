package com.yuly.quasar.service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.yuly.quasar.entity.SatellitesEntity;
import com.yuly.quasar.enums.SatelliteType;
import com.yuly.quasar.exception.BusinessException;
import com.yuly.quasar.model.*;
import com.yuly.quasar.parameter.ParameterSingleton;
import com.yuly.quasar.repository.SatellitesRepository;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SatelliteService implements ISatelliteService {

  private final DecimalFormat df = new DecimalFormat("#.##");

  private SatellitesRepository satellitesRepository;

  private String getMessage(List<String[]> msgSatellite) throws Exception {
    String[] result = new String[msgSatellite.get(0).length];
    for (int i = 0; i < msgSatellite.get(0).length; i++) {
      int finalI = i;
      msgSatellite.forEach(
        msg -> {
          try {
            fillMessageResult(result, msg, finalI);
          } catch (Exception e) {
            throw new BusinessException(e.getMessage());
          }
        }
      );
    }
    return String.join(" ", result);
  }

  private void fillMessageResult(String[] result, String[] msg, int index)
    throws Exception {
    if (!msg[index].isEmpty()) {
      if (compareWord(result[index], msg[index])) {
        throw new BusinessException("No se puede obtener mensaje");
      } else {
        result[index] = msg[index];
      }
    }
  }

  private boolean compareWord(String arg1, String arg2) {
    return (
      !Optional.ofNullable(arg1).orElse("").isEmpty() && !arg2.equalsIgnoreCase(arg1)
    );
  }

  private PositionModel getLocation(List<Double> distances) throws Exception {
    SatelliteDefaultModel kenobi = ParameterSingleton
      .getInstance()
      .getSatellitePositionByType(SatelliteType.KENOBI);
    SatelliteDefaultModel skywalker = ParameterSingleton
      .getInstance()
      .getSatellitePositionByType(SatelliteType.SKYWALKER);
    SatelliteDefaultModel sato = ParameterSingleton
      .getInstance()
      .getSatellitePositionByType(SatelliteType.SATO);
    double[][] positions = new double[][] {
      { kenobi.getX(), kenobi.getY() },
      { skywalker.getX(), skywalker.getY() },
      { sato.getX(), sato.getY() },
    };

    Double[] distanceSat = new Double[distances.size()];
    distanceSat = distances.toArray(distanceSat);

    double[] satelliteDistances = Stream
      .of(distanceSat)
      .mapToDouble(Double::doubleValue)
      .toArray();

    NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
      new TrilaterationFunction(positions, satelliteDistances),
      new LevenbergMarquardtOptimizer()
    );
    LeastSquaresOptimizer.Optimum optimum = solver.solve();

    double[] centroid = optimum.getPoint().toArray();

    return PositionModel
      .builder()
      .x(df.parse(df.format(centroid[0])).doubleValue())
      .y(df.parse(df.format(centroid[1])).doubleValue())
      .build();
  }

  @Override
  public GetOperationResponseModel getOperation(GetOperationRequestModel satellites)
    throws Exception {
    List<String[]> msgSatellite = new ArrayList<>();
    List<Double> distances = new ArrayList<>();
    for (SatelliteModel s : satellites.getSatellites()) {
      String[] msg = s.getMessage();
      msgSatellite.add(msg);

      Double distance = s.getDistance();
      distances.add(distance);
    }
    String msgSat = getMessage(msgSatellite);
    PositionModel position = getLocation(distances);

    saveSatellite(satellites.getSatellites());

    return GetOperationResponseModel.builder().position(position).message(msgSat).build();
  }

  @Override
  public GetOperationResponseModel getOperationBySatellite(
    GetOperationBySatRequestModel satellite,
    String name
  ) throws Exception {
    Iterable<SatellitesEntity> satellites = findSatellites();
    if (satellites.iterator().hasNext()) {
      GetOperationRequestModel getOptReq = new GetOperationRequestModel();
      getOptReq.setSatellites(
        Arrays.asList(
          satellites.iterator().next().getKenobi(),
          satellites.iterator().next().getSkywalker(),
          satellites.iterator().next().getSato()
        )
      );
      if (satellite != null) {
        getOptReq
          .getSatellites()
          .stream()
          .filter(i -> i.getName().equalsIgnoreCase(name))
          .map(
            j -> {
              j.setDistance(satellite.getDistance());
              j.setMessage(satellite.getMessage());
              return j;
            }
          )
          .collect(Collectors.toList());
      }

      return this.getOperation(getOptReq);
    } else {
      throw new BusinessException("No hay suficiente información");
    }
  }

  private void saveSatellite(List<SatelliteModel> satellites) {
    SatellitesEntity satellitesEntity = new SatellitesEntity();
    satellitesEntity.setKenobi(
      satellites
        .stream()
        .filter(i -> i.getName().equalsIgnoreCase("kenobi"))
        .findFirst()
        .get()
    );
    satellitesEntity.setSkywalker(
      satellites
        .stream()
        .filter(i -> i.getName().equalsIgnoreCase("skywalker"))
        .findFirst()
        .get()
    );
    satellitesEntity.setSato(
      satellites
        .stream()
        .filter(i -> i.getName().equalsIgnoreCase("sato"))
        .findFirst()
        .get()
    );
    if (satellitesRepository.findAll().iterator().hasNext()) {
      satellitesEntity.setId(satellitesRepository.findAll().iterator().next().getId());
    }
    satellitesRepository.save(satellitesEntity);
  }

  private Iterable<SatellitesEntity> findSatellites() {
    return satellitesRepository.findAll();
  }
}
