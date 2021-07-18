package com.json_xml.parse.repositories;

import com.json_xml.parse.models.entities.partCarSale.CarEntity;
import com.json_xml.parse.models.entities.partCarSale.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
    Set<CustomerEntity> findAllBySalesIsNotNull();
}
