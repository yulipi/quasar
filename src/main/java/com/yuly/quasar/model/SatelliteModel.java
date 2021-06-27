package com.yuly.quasar.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SatelliteModel {

  private String name;
  private Double distance;
  private String[] message;
}
