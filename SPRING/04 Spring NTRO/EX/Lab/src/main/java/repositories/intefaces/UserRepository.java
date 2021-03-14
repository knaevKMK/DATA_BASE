package repositories.intefaces;

import entities.Account;
import entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
//    void save(User user);
//
//    Account findOne(long primaryKey);
//
//    List<User> findAll();
//
//    long count();
//
//    void delete(User user);
//
//    User exists(long primaryKey);
}
