package com.hafidelmoudden.springpatientsmanagement.web;

import com.hafidelmoudden.springpatientsmanagement.entities.Patient;
import com.hafidelmoudden.springpatientsmanagement.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patientsList", patients);
        return "patients";
    }
}
