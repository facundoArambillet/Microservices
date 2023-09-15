package com.tutorial.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDto {
    private int idUser;
    private String userName;
    private String userPassword;
    private String rol;
}
