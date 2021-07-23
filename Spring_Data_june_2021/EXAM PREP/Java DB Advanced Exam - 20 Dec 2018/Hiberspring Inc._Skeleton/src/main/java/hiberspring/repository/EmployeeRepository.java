package hiberspring.repository;

import hiberspring.domain.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Query("select  e from EmployeeEntity e " +
            " order by concat(e.firstName,' ', e.lastName) asc , " +
            "length(e.position) DESC ")

    Set<EmployeeEntity> findAllByOrderByFullNameAscThanByPositionLengthDesc();
@Query("select e from EmployeeEntity  e join e.card c where c.number= :cardNumber")
    EmployeeEntity findByCardNumber(String cardNumber);
}
