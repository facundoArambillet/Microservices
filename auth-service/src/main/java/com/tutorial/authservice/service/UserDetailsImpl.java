package com.tutorial.authservice.service;

import com.tutorial.authservice.model.AuthUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDetailsImpl implements UserDetails {
    private int idUser;
    private String userName;
    private String userPassword;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(int idUser, String userName, String userPassword, Collection<? extends GrantedAuthority> authorities) {
        this.idUser = idUser;
        this.userName = userName;
        this.userPassword = userPassword;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(AuthUser authUser) {
        Collection<GrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority(authUser.getRol())
        );
//        Collection<GrantedAuthority> authorities =
//            Stream.of(authUser.getRol()).map(rol -> new SimpleGrantedAuthority(rol)).collect(Collectors.toList());
        return new UserDetailsImpl(
                authUser.getIdUser(),
                authUser.getUserName(),
                authUser.getUserPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
