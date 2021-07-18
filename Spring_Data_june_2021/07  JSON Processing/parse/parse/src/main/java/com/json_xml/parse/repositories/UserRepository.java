package com.json_xml.parse.repositories;

import com.json_xml.parse.models.entities.partUserProductCategoriy.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select u from  UserEntity  u where (select count(p) from ProductEntity p where p.seller.id=u.id)>0 " +
            "order by u.lastName, u.firstName")
    Set<UserEntity> findAllByProductsHasBuyer();

    @Query("select u from  UserEntity  u " +
            "join u.products p " +
            "WHERE p.buyer is NOT NULL" +
            " order by u.products.size   DESC " +
            ",u.lastName")
    Set<UserEntity> getSellersWithSoldOrderBySoldProductDescThenByLastName();
}
