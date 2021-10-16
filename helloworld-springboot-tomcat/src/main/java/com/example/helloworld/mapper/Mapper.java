package com.example.helloworld.mapper;

import com.example.helloworld.entity.Account;
import com.example.helloworld.entity.Role;
import com.example.helloworld.model.AccountDTO;
import com.example.helloworld.model.RoleDTO;

public class Mapper {
    public static AccountDTO accountDTO(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setPhone(account.getPhone());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setRoleId(account.getRoleId());
        return accountDTO;
    }

    public static RoleDTO toRoleDTO(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }
}
