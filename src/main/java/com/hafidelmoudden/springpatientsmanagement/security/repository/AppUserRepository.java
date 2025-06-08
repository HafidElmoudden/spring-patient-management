package com.hafidelmoudden.springpatientsmanagement.security.repository;

import com.hafidelmoudden.springpatientsmanagement.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
