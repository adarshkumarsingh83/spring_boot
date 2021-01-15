package com.espark.adarsh;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by adarsh_k on 4/6/2017.
 */
@Service
class AccountUserDetailsService implements UserDetailsService {

    private UserBeanRepository userBeanRepository;

    public AccountUserDetailsService(UserBeanRepository accountRepository) {
        this.userBeanRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userBeanRepository.findByUsername(username).map(account -> new User(account.getUsername()
                ,account.getPassword(),account.isActive(),account.isActive(),account.isActive(),account.isActive()
                , AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_USER")))
                .orElseThrow(()->new UsernameNotFoundException("User Not Found in the System "+username));

    }
}
