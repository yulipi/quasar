package com.yuly.quasar.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOperationRequestModel {

  private List<SatelliteModel> satellites;
}
