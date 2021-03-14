package repositories.intefaces;

import entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
//    void save(Account account);
//
//    Account findOne(long primaryKey);
//
//    List<Account> findAll();
//
//    long count();
//
//    void delete(Account account);
//
//    Account exists(long primaryKey);
Account findAccountById(Long id);

}
