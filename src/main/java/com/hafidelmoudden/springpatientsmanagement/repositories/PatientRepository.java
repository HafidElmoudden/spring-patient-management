package com.hafidelmoudden.springpatientsmanagement.repositories;

import com.hafidelmoudden.springpatientsmanagement.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
