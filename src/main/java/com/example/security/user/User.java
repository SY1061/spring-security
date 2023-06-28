package com.example.security.user;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class User {
    @Id
    private int id;
    private String username;
    private String password;
    private String authority;
}
