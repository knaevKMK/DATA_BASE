package com.json_xml.parse.repositories;

import com.json_xml.parse.models.entities.partCarSale.PartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<PartEntity, Long> {
}
