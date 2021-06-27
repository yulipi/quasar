package com.yuly.quasar.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class PositionModel {

  private Double x;
  private Double y;
}
