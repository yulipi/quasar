package com.yuly.quasar.model;

import com.yuly.quasar.enums.SatelliteType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class SatelliteDefaultModel extends PositionModel {

  private SatelliteType type;
}
