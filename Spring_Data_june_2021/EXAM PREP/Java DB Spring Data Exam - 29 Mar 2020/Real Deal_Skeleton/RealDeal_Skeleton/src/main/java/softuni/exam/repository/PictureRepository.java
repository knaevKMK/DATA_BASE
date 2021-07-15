package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entities.PictureEntity;

import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Integer> {


    Optional<PictureEntity> findByName(String name);

}
