package com.hafidelmoudden.springpatientsmanagement.security.repository;

import com.hafidelmoudden.springpatientsmanagement.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
}
