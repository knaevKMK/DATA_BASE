package com.json_xml.parse.repositories;

import com.json_xml.parse.models.entities.partCarSale.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity,Long> {
}
