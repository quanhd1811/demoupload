package com.example.helloworld.entity;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String id;
    private String phone;
    private String password;
    private int roleId;
}