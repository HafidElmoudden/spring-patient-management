package com.hafidelmoudden.springpatientsmanagement;

import com.hafidelmoudden.springpatientsmanagement.entities.Patient;
import com.hafidelmoudden.springpatientsmanagement.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class SpringPatientsManagementApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringPatientsManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null, "Hafid", LocalDate.of(1990, 5, 15), false, 15));
        patientRepository.save(new Patient(null, "Yassine", LocalDate.of(1985, 8, 20), false, 10));
        patientRepository.save(new Patient(null, "Omar", LocalDate.of(1995, 12, 10), true, 20));
    }
}
