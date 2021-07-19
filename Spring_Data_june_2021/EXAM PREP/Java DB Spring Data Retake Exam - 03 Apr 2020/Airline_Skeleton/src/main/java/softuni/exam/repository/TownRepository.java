package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entities.TownEntity;

@Repository
public interface TownRepository extends JpaRepository<TownEntity,Long> {

    TownEntity findByName(String name);

}
