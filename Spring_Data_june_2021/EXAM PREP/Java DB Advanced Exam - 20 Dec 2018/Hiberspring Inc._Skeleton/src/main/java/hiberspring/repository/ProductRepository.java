package hiberspring.repository;

import hiberspring.domain.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

}
