package com.example.helloworld.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.helloworld.constant.ResponseStatus;
import com.example.helloworld.model.AccountDTO;
import com.example.helloworld.model.MessageResponse;
import com.example.helloworld.model.RoleDTO;
import com.example.helloworld.service.AccountService;
import com.example.helloworld.service.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("api/account")
public class AccountController extends BaseController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO accountDTO) throws ExecutionException,InterruptedException{
        boolean isSuccess = accountService.saveAccount(accountDTO);
        String message = isSuccess ? "Create Account Successfully" : "Create Account fail";
        String status = isSuccess ? ResponseStatus.SUCCESS : ResponseStatus.FAIL;
        return toResponse(new MessageResponse(status,message, accountService.getAccountDetailsByPhone(accountDTO.getPhone())));
    }
    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Ironcap ")) {
            try {
                String refreshToken = authorizationHeader.substring("Ironcap ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                AccountDTO accountDTO = accountService.getAccountDetailsByPhone(username);
                String[] roles = decodedJWT.getClaim("role").asArray(String.class);
                List<RoleDTO> listRole = new ArrayList<>();
                String accessToken = JWT.create()
                        .withSubject(accountDTO.getPhone())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 20 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("role", listRole.add(roleService.findRoleById(accountDTO.getRoleId())))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessToken", accessToken);
                tokens.put("refreshToken", refreshToken);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("errorMessage", exception.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refreshtoken is missing");
        }
    }
}
