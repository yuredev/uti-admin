package springboot.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return service.getAll();
    }
    @GetMapping(path = {"/{id}"})
    public Patient getOne(@PathVariable Long cpf) {
        return service.getOne(cpf);
    }
    @PostMapping
    public Patient insert(@RequestBody Patient patient) {
        return service.save(patient);
    }
    @PutMapping(value = "/{cpf}")
    public ResponseEntity<Patient> update(@PathVariable Long cpf, @RequestBody Patient patient) {
        Patient patientFound = service.getOne(cpf);
        if (patientFound == null) {
            return ResponseEntity.notFound().build();
        }
        Patient patientUpdated = service.save(patient);
        return ResponseEntity.ok().body(patientUpdated);
    }
}