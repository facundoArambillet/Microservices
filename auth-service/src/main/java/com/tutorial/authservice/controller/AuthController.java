package com.tutorial.authservice.controller;

import com.tutorial.authservice.dto.AuthUserDto;
import com.tutorial.authservice.dto.TokenDto;
import com.tutorial.authservice.model.AuthUser;
import com.tutorial.authservice.security.JwtFilter;
import com.tutorial.authservice.security.JwtProvider;
import com.tutorial.authservice.service.AuthService;
import com.tutorial.authservice.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUser authUser) {
        TokenDto tokenDto = authService.login(authUser);
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthUser user) {
        return authService.register(user);
    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestParam("token") String token) {
        return jwtProvider.validateToken(token);
    }
    @GetMapping("/{idUser}")
    public Optional<AuthUser> getById(@PathVariable("idUser") int idUser) {
        return authService.getUserById(idUser);
    }
}
