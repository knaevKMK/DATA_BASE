package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entities.CarEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Integer> {
    @Override
    Optional<CarEntity> findById(Integer integer);

    @Query("SELECT c FROM CarEntity c order by size(c.pictures) DESC , c.make")
    List<CarEntity> getAllOrderByPictureCountDescThenByMake();
}
