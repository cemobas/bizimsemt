package com.krakus.bizimsemt.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BizimUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BizimUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(name);
        if(null != user){
            BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
            LOGGER.debug("foobar verified ---->  " + pe.matches("foobar", user.getPassword()));
            LOGGER.debug("password verified ---->  " + pe.matches("password", user.getPassword()));
            return new BizimUserPrincipal(user);
        }
        throw new UsernameNotFoundException("cannot find username: " + name);
    }
}
