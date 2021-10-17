package com.example.helloworld.service;

import com.example.helloworld.model.RoleDTO;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public interface RoleService {
    RoleDTO findRoleById(int id) throws ExecutionException,InterruptedException;

}
