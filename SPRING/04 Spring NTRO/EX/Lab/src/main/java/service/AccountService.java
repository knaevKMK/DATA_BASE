package service;

import entities.Account;
import entities.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account createUserAccount(User user, Account account);

    void withdrawMoney(BigDecimal amount, Long accountId);

    void depositMoney(BigDecimal amount, Long accountId) throws IllegalAccessException;

    void transferMoney(BigDecimal amount, Long accountId, Long toAccountId) throws IllegalAccessException;

    List<Account> getAllAccounts();
}
