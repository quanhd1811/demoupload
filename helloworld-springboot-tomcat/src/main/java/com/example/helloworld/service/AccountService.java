package com.example.helloworld.service;

import com.example.helloworld.entity.Account;
import com.example.helloworld.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountByPhone(String phone) throws ExecutionException, InterruptedException {
        Account account = accountRepository.getAccountByPhone(phone);
        if (account != null) {
            return account;
        } else return null;
    }

    public List<Account> getAllAccount() throws ExecutionException, InterruptedException {
        List<Account> listAccount = new ArrayList<>();
        listAccount = accountRepository.getAllAccount();
        return listAccount;
    }
}
