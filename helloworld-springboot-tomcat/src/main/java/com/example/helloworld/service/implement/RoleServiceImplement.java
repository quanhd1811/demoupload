package com.example.helloworld.service.implement;

import com.example.helloworld.entity.Role;
import com.example.helloworld.mapper.Mapper;
import com.example.helloworld.model.RoleDTO;
import com.example.helloworld.repository.RoleRepository;
import com.example.helloworld.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class RoleServiceImplement implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public RoleDTO findRoleById(int id) throws ExecutionException, InterruptedException {
        Role role = roleRepository.getRoleById(id);
        if(role!=null){
            RoleDTO roleDTO = Mapper.toRoleDTO(role);
            return roleDTO;
        }
        return  null;
    }
}
