package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entities.PassengerEntity;

import java.util.Set;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerEntity, Long> {
    @Query("select p from PassengerEntity p " +
            "order by size(p.tickets) DESC , p.email")
Set<PassengerEntity> getAllOrderByCountTicketDescThenByEmailAsc();

    PassengerEntity findByEmail(String email);
}
