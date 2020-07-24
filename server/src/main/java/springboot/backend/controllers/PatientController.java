package springboot.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.backend.models.Patient;
import springboot.backend.services.PatientService;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = {"/{cpf}"})
    public ResponseEntity<Patient> getOne(@PathVariable Long cpf) {
        Patient patientFound = service.getOne(cpf);
        if (patientFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(patientFound);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Patient patient) {
        try {
            service.save(patient);
            return ResponseEntity.status(201).build(); // CREATED
        } catch (Exception e) {
            return ResponseEntity.status(500).build();  // INTERNAL SERVER ERROR
        }
    }

    @PutMapping(value = "/{cpf}")
    public ResponseEntity<?> update(@PathVariable Long cpf, @RequestBody Patient patient) {
        Patient patientFound = service.getOne(cpf);
        if (patientFound == null) {
            return ResponseEntity.notFound().build();
        }
        service.save(patient);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{cpf}")
    public ResponseEntity<?> delete(@PathVariable Long cpf) {
        Patient patientFound = service.getOne(cpf);
        if (patientFound == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(patientFound);
        return ResponseEntity.ok().build();
    }
}