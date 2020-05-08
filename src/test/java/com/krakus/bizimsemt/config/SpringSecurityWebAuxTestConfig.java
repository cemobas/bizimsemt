package com.krakus.bizimsemt.config;

import com.krakus.bizimsemt.auth.BizimUserPrincipal;
import com.krakus.bizimsemt.auth.User;
import org.bson.types.ObjectId;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        User basicUser = new User(new ObjectId("5eb4684550f1e2cd98b77c70"), "user", "password");
        BizimUserPrincipal basicUserPrincipal = new BizimUserPrincipal(basicUser);

        User managerUser = new User(new ObjectId("5eb4683050f1e2cd98b77c64"), "foo", "bar");
        BizimUserPrincipal managerUserPrincipal = new BizimUserPrincipal(managerUser);

        return new InMemoryUserDetailsManager(Arrays.asList(
                basicUserPrincipal, managerUserPrincipal
        ));
    }
}