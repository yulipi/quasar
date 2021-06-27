package com.yuly.quasar.parameter;

import com.yuly.quasar.enums.SatelliteType;
import com.yuly.quasar.model.PositionModel;
import com.yuly.quasar.model.SatelliteDefaultModel;
import java.util.Arrays;
import java.util.List;

public class ParameterSingleton {

  private static ParameterSingleton instance;

  private ParameterSingleton() {}

  public static ParameterSingleton getInstance() {
    if (instance == null) {
      instance = new ParameterSingleton();
    }
    return instance;
  }

  public SatelliteDefaultModel getSatellitePositionByType(SatelliteType type) {
    switch (type) {
      case KENOBI:
        return SatelliteDefaultModel
          .builder()
          .x(-500.0)
          .y(-200.0)
          .type(SatelliteType.KENOBI)
          .build();
      case SKYWALKER:
        return SatelliteDefaultModel
          .builder()
          .x(100.0)
          .y(-100.0)
          .type(SatelliteType.SKYWALKER)
          .build();
      case SATO:
        return SatelliteDefaultModel
          .builder()
          .x(500.0)
          .y(100.0)
          .type(SatelliteType.SATO)
          .build();
      default:
        return null;
    }
  }
}
