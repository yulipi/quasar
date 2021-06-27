package com.yuly.quasar.service;

import com.yuly.quasar.model.GetOperationBySatRequestModel;
import com.yuly.quasar.model.GetOperationRequestModel;
import com.yuly.quasar.model.GetOperationResponseModel;

public interface ISatelliteService {
  GetOperationResponseModel getOperation(GetOperationRequestModel satellites)
    throws Exception;
  GetOperationResponseModel getOperationBySatellite(
    GetOperationBySatRequestModel satellite,
    String name
  ) throws Exception;
}
