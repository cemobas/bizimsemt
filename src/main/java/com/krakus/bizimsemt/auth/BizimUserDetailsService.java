package com.krakus.bizimsemt.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BizimUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthGroupRepository authGroupRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(name);
        if(null != user){
            List<AuthGroup> authGroups = this.authGroupRepository.findByUsername(name);
            return new BizimUserPrincipal(user, authGroups);
        }
        throw new UsernameNotFoundException("cannot find username: " + name);
    }
}
