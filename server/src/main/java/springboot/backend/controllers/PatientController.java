package springboot.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.backend.models.Patient;
import springboot.backend.services.PatientService;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private PatientService service;
    @Autowired
    public void setService(PatientService service) {
        this.service = service;
    }
    @GetMapping
    public List<Patient> list() {
        return service.findAll();
    }
    @PostMapping
    public Patient insert(@RequestBody Patient patient) {
        return service.save(patient);
    }

}
