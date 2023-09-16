package com.tutorial.authservice.service;

import com.tutorial.authservice.dto.AuthUserDto;
import com.tutorial.authservice.dto.TokenDto;
import com.tutorial.authservice.model.AuthUser;
import com.tutorial.authservice.repository.AuthUserRepository;
import com.tutorial.authservice.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    AuthUserRepository authUserRepository;
    @Autowired
    JwtProvider jwtProvider;
    public TokenDto login(AuthUser authUser) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authUser.getUserName(),
                        authUser.getUserPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authUser);
        return new TokenDto(token);
    }
}
