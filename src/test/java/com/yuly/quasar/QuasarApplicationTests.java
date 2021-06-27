package com.yuly.quasar;

import static org.assertj.core.api.Assertions.*;

import com.google.gson.Gson;
import com.yuly.quasar.exception.BusinessException;
import com.yuly.quasar.model.GetOperationRequestModel;
import com.yuly.quasar.model.GetOperationResponseModel;
import com.yuly.quasar.service.SatelliteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuasarApplicationTests {

  @Autowired
  private SatelliteService satelliteService;

  @Test
  void getPositionSuccess() throws Exception {
    GetOperationRequestModel req = new Gson()
      .fromJson(ConstantsTest.GET_POSITION_REQ, GetOperationRequestModel.class);

    GetOperationResponseModel response = satelliteService.getOperation(req);
    assertThat(response.getPosition().getX()).isEqualTo(-58.32);
    assertThat(response.getPosition().getY()).isEqualTo(-69.55);
    assertThat(response.getMessage()).isEqualTo("este es un mensaje secreto");
  }

  @Test
  void getPositionError() {
    GetOperationRequestModel req = new Gson()
      .fromJson(ConstantsTest.GET_POSITION_REQ_ERROR, GetOperationRequestModel.class);
    Assertions.assertThrows(
      BusinessException.class,
      () -> {
        satelliteService.getOperation(req);
      }
    );
  }
}
