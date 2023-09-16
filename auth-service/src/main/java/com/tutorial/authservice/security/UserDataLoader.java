package com.tutorial.authservice.security;

import com.tutorial.authservice.model.AuthUser;
import com.tutorial.authservice.repository.AuthUserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDataLoader implements ApplicationRunner {
    private final AuthUserRepository authUserRepository;

    public UserDataLoader(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        // Agregar usuarios de ejemplo a la base de datos
        AuthUser user1 = new AuthUser(1,"test@test.com","123456789","ADMIN");
        authUserRepository.save(user1);

        AuthUser user2 = new AuthUser(2,"test2@test.com","123456789","ADMIN");
        authUserRepository.save(user2);

    }

}