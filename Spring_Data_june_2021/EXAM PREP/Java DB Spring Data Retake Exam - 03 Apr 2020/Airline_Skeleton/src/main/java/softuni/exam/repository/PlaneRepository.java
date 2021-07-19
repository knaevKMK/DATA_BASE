package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entities.PlaneEntity;

@Repository
public interface PlaneRepository extends JpaRepository<PlaneEntity, Long> {

    PlaneEntity findByRegisterNumber(String registerNumber);
}
