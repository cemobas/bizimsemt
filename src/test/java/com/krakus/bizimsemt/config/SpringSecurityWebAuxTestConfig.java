package com.krakus.bizimsemt.config;

import com.krakus.bizimsemt.auth.AuthGroup;
import com.krakus.bizimsemt.auth.BizimUserPrincipal;
import com.krakus.bizimsemt.auth.User;
import org.bson.types.ObjectId;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        User basicUser = new User(ObjectId.get(), "user", "password");
        List<AuthGroup> basicAuthGroup = new ArrayList<>();
        basicAuthGroup.add(new AuthGroup(ObjectId.get(), "user", "ROLE_USER"));

        BizimUserPrincipal basicUserPrincipal = new BizimUserPrincipal(basicUser, basicAuthGroup);

        User managerUser = new User(ObjectId.get(), "admin", "password");
        List<AuthGroup> managerAuthGroup = new ArrayList<>();
        managerAuthGroup.add(new AuthGroup(ObjectId.get(), "admin", "ROLE_USER"));
        managerAuthGroup.add(new AuthGroup(ObjectId.get(), "admin", "ROLE_ADMIN"));

        BizimUserPrincipal managerUserPrincipal = new BizimUserPrincipal(managerUser, managerAuthGroup);

        return new InMemoryUserDetailsManager(Arrays.asList(
                basicUserPrincipal, managerUserPrincipal
        ));
    }
}