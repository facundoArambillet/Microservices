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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    AuthUserRepository authUserRepository;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    PasswordEncoder passwordEncoder;
    public TokenDto login(AuthUser authUser) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authUser.getUserName(), authUser.getUserPassword());
        Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl userToken = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtProvider.createToken(userToken);
        return new TokenDto(token);
    }

    public String register(AuthUser authUser) {
        Optional<AuthUser> user = authUserRepository.findByUserName(authUser.getUserName());
        if(!user.isPresent()) {
            authUser.setUserPassword(passwordEncoder.encode(authUser.getUserPassword()));
            authUserRepository.save(authUser);
            return "User added";
        }
        return "The user exist";
    }

    public Optional<AuthUser> getUserById(int idUser) {
        Optional<AuthUser> user = authUserRepository.findById(idUser);
        return user;
    }
}
