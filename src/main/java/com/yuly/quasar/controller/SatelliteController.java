package com.yuly.quasar.controller;

import com.yuly.quasar.model.GetOperationBySatRequestModel;
import com.yuly.quasar.model.GetOperationRequestModel;
import com.yuly.quasar.model.GetOperationResponseModel;
import com.yuly.quasar.service.SatelliteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/")
public class SatelliteController {

  private final SatelliteService satelliteService;

  @PostMapping("topsecret")
  private ResponseEntity<GetOperationResponseModel> getOperation(
    @RequestBody GetOperationRequestModel satellites
  ) throws Exception {
    return ResponseEntity.ok(satelliteService.getOperation(satellites));
  }

  @PostMapping("topsecret_split/{satellite_name}")
  private ResponseEntity<GetOperationResponseModel> getOperationBySatellite(
    @RequestBody GetOperationBySatRequestModel satellite,
    @PathVariable("satellite_name") String name
  ) throws Exception {
    return ResponseEntity.ok(satelliteService.getOperationBySatellite(satellite, name));
  }

  @GetMapping("topsecret_split/{satellite_name}")
  private ResponseEntity<GetOperationResponseModel> getOperationBySatelliteGet(
    @PathVariable("satellite_name") String name
  ) throws Exception {
    return ResponseEntity.ok(satelliteService.getOperationBySatellite(null, name));
  }
}
