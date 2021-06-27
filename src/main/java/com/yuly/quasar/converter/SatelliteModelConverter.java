package com.yuly.quasar.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuly.quasar.model.SatelliteModel;

public class SatelliteModelConverter
  implements DynamoDBTypeConverter<String, SatelliteModel> {

  @Override
  public String convert(SatelliteModel object) {
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonStr = null;

    try {
      jsonStr = objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return jsonStr;
  }

  @Override
  public SatelliteModel unconvert(String s) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(s, SatelliteModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
