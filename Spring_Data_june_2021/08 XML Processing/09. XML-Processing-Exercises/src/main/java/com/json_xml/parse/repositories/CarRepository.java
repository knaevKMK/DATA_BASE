package com.json_xml.parse.repositories;

import com.json_xml.parse.models.entities.partCarSale.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<CarEntity,Long> {
    Set<CarEntity> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);
}
