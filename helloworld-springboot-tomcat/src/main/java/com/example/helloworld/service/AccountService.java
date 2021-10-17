package com.example.helloworld.service;

import com.example.helloworld.model.AccountDTO;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public interface AccountService {
    AccountDTO getAccountByPhoneAndPassword(String phone, String password) throws ExecutionException,InterruptedException;

    boolean createAccount(AccountDTO accountDTO);

    AccountDTO getAccountDetailsByPhone(String phone) throws ExecutionException,InterruptedException;

    boolean saveAccount(AccountDTO accountDTO) throws ExecutionException, InterruptedException;

    boolean changeRoleOfAcount(String id, int roleId) throws ExecutionException,InterruptedException;


}
