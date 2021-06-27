package com.yuly.quasar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GetOperationResponseModel {

  private PositionModel position;
  private String message;
}
