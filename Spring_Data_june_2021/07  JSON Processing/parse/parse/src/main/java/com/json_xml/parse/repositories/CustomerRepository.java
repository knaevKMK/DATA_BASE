package com.json_xml.parse.repositories;

import com.json_xml.parse.models.entities.partCarSale.CarEntity;
import com.json_xml.parse.models.entities.partCarSale.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
}
