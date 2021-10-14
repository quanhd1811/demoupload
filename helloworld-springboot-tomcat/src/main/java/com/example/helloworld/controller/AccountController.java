package com.example.helloworld.controller;

import com.example.helloworld.entity.Account;
import com.example.helloworld.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;


    @GetMapping("/detail/{phone}")
    public Account getAccountByPhone(@PathVariable String phone) throws ExecutionException, InterruptedException {
        return accountService.getAccountByPhone(phone);
    }

    @GetMapping("/all")
    public List<Account> getAllAccount() throws ExecutionException, InterruptedException {
        return accountService.getAllAccount();
    }
}
