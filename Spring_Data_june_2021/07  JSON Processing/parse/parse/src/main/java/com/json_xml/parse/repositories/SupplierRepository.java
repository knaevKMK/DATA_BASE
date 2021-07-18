package com.json_xml.parse.repositories;

import com.json_xml.parse.models.entities.partCarSale.CarEntity;
import com.json_xml.parse.models.entities.partCarSale.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {
}
