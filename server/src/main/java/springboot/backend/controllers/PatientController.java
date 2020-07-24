package springboot.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping(path = {"/{cpf}"})
    public ResponseEntity<Patient> getByCpf(@PathVariable Long cpf) {
        Patient patientFound = service.getByCpf(cpf);
        if (patientFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(patientFound);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Patient patient) {
        try {
            Patient patientFound = service.getByCpf(patient.getCpf());
            if (patientFound != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            service.save(patient);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(path = "/{cpf}")
    public ResponseEntity<?> update(@PathVariable Long cpf, @RequestBody Patient patient) {
        Patient patientFound = service.getByCpf(cpf);
        if (patientFound == null) {
            return ResponseEntity.notFound().build();
        }
        service.save(patient);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{cpf}")
    public ResponseEntity<?> delete(@PathVariable Long cpf) {
        Patient patientFound = service.getByCpf(cpf);
        if (patientFound == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(patientFound);
        return ResponseEntity.ok().build();
    }
}