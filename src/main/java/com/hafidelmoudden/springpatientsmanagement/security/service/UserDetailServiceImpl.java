package com.hafidelmoudden.springpatientsmanagement.security.service;

import lombok.AllArgsConstructor;
import com.hafidelmoudden.springpatientsmanagement.security.entities.AppUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(username);
        if (appUser == null)
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        UserDetails userDetails = User
                .withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(appUser.getRoles().stream().map(u -> u.getRole()).toArray(String[]::new))
                .build();
        return userDetails;
    }
}
