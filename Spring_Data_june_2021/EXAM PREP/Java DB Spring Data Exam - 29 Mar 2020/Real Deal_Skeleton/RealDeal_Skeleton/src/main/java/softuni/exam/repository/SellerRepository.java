package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entities.SellerEntity;

import java.util.Optional;

@Repository
public interface SellerRepository  extends JpaRepository<SellerEntity, Integer> {
    Optional<SellerEntity> findByEmail(String email);
}
