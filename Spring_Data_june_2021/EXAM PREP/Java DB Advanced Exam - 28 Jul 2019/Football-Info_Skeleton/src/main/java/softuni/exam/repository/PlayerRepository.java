package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.domain.entities.Player;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("select p from Player p join p.team t where t.name= :name order by p.id")
    Set<Player> findAllByTeamName(String name);

    Set<Player> findAllBySalaryIsAfterOrderBySalaryDesc(BigDecimal salary);
}
