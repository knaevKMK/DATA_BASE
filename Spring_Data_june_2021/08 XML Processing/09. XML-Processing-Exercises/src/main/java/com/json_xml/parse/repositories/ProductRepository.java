package com.json_xml.parse.repositories;

import com.json_xml.parse.models.entities.partUserProductCategoriy.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal down, BigDecimal up);

}
