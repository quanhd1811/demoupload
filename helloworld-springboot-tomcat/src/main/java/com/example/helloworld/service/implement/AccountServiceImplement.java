package com.example.helloworld.service.implement;

import com.example.helloworld.entity.Account;
import com.example.helloworld.entity.Role;
import com.example.helloworld.model.AccountDTO;
import com.example.helloworld.repository.AccountRepository;
import com.example.helloworld.repository.RoleRepository;
import com.example.helloworld.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImplement implements AccountService, UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;


    @Override
    public AccountDTO getAccountByPhoneAndPassword(String phone, String password) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public boolean createAccount(AccountDTO accountDTO) {
        return false;
    }

    @Override
    public AccountDTO getAccountDetailsByPhone(String phone) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public boolean saveAccount(AccountDTO accountDTO) throws ExecutionException, InterruptedException {
        return false;
    }

    @Override
    public boolean changeRoleOfAcount(String id, int roleId) throws ExecutionException, InterruptedException {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String userPhone) throws UsernameNotFoundException {
        Account account = null;
        Role role = null;
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        try {
            account = accountRepository.getAccountByPhone(userPhone);
            if (account == null) {
                throw new UsernameNotFoundException("User not found in db");
            } else {
                log.info("User found in database: {}",userPhone);
                System.out.println("User found in database: " + userPhone);
                role = roleRepository.getRoleById(account.getRoleId());
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return new org.springframework.security.core.userdetails.User(account.getPhone(), account.getPassword(), authorities);
    }
}
