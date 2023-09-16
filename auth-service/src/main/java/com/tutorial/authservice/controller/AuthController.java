package com.tutorial.authservice.controller;

import com.tutorial.authservice.dto.AuthUserDto;
import com.tutorial.authservice.dto.TokenDto;
import com.tutorial.authservice.model.AuthUser;
import com.tutorial.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUser authUser) {
        TokenDto tokenDto = authService.login(authUser);
        return ResponseEntity.ok(tokenDto);
    }

}
