package com.yuly.quasar.repository;

import com.yuly.quasar.entity.SatellitesEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface SatellitesRepository extends CrudRepository<SatellitesEntity, String> {}
