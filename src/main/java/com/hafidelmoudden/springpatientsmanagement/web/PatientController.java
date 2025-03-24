package com.hafidelmoudden.springpatientsmanagement.web;

import com.hafidelmoudden.springpatientsmanagement.entities.Patient;
import com.hafidelmoudden.springpatientsmanagement.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
                        @RequestParam(name = "size", defaultValue = "4") int s,
                        @RequestParam(name = "keyword", defaultValue = "") String kw) {
        Page<Patient> patientsPage = patientRepository.findByNomContains(kw, PageRequest.of(p, s));
        model.addAttribute("patientsList", patientsPage.getContent());
        model.addAttribute("pages", new int[patientsPage.getTotalPages()]);
        model.addAttribute("currentPage", p);
        model.addAttribute("keyword", kw);
        return "patients";
    }

    @GetMapping("/delete")
    public String delete(Model model, @RequestParam(name = "id") Long id,
                         @RequestParam(name = "keyword", defaultValue = "") String keyword,
                         @RequestParam(name = "page", defaultValue = "0") int page) {
        patientRepository.deleteById(id);
        return "redirect:/index?" + (keyword.isEmpty() ? "" : "keyword=" + keyword + "&") + "page=" + page;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/index";
    }
}
