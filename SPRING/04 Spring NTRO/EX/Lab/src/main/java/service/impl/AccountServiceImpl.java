package service.impl;

import entities.Account;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.intefaces.AccountRepository;
import service.AccountService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepo;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public Account createUserAccount(User user, Account account) {
        account.setId(null);
        account.setUser(user);
        user.getAccounts().add(account);
        return accountRepo.save(account);
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long accountId) {
        Account account = accountRepo.findById(accountId).orElseThrow(() ->
                new RuntimeException());
        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Amount is not enough");
        }
        account.setBalance((account.getBalance().subtract(amount)));
    }
    //commit transacton -@Transactional

    @Override
    public void depositMoney(BigDecimal amount, Long accountId) throws IllegalAccessException {
        Account account = accountRepo.findById(accountId).orElseThrow(() ->
                new IllegalAccessException("Account does not exist"));
        account.setBalance(account.getBalance().subtract(amount));
    }

    @Override
    public void transferMoney(BigDecimal amount, Long accountId, Long toAccountId) throws IllegalAccessException {
        depositMoney(amount, toAccountId);
        withdrawMoney(amount, accountId);
    }
    //commt transaction

    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }
}
