package com.espark.adarsh.service;


import com.espark.adarsh.bean.UserBean;
import com.espark.adarsh.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthenticationService implements UserDetailsService {

    @Qualifier(value = "userRepository")
	@Autowired(required = true)
	private UserRepositoryImpl userRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserBean userBean = userRepository.getUserInfo(username);
		GrantedAuthority authority = new SimpleGrantedAuthority(userBean.getRole());
		UserDetails userDetails = (UserDetails)new User(userBean.getUsername(),
				userBean.getPassword(), Arrays.asList(authority));
		return userDetails;
	}
}
