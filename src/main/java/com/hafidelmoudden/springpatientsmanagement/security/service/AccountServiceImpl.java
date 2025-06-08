package com.hafidelmoudden.springpatientsmanagement.security.service;

import lombok.AllArgsConstructor;
import com.hafidelmoudden.springpatientsmanagement.security.entities.AppRole;
import com.hafidelmoudden.springpatientsmanagement.security.entities.AppUser;
import com.hafidelmoudden.springpatientsmanagement.security.repository.AppRoleRepository;
import com.hafidelmoudden.springpatientsmanagement.security.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    AppUserRepository appUserRepository;
    AppRoleRepository appRoleRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser != null) {
            return appUser;
        }
        if (!password.equals(confirmPassword))
            throw new RuntimeException("Passwords do not match");
        appUser = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .roles(new ArrayList<>())
                .build();
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if (appRole != null) {
            return appRole;
        }
        appRole = AppRole.builder().role(role).build();
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findById(role).get();
        if (appUser.getRoles().contains(appRole)) {
            return;
        }
        appUser.getRoles().add(appRole);

    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findById(role).get();
        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
