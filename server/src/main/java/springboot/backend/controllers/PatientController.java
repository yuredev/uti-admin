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
@CrossOrigin(origins = {"http://localhost:3000"}, exposedHeaders = "X-Total-Count")
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
    public ResponseEntity<Patient> getByCpf(@PathVariable Integer id) {
        Patient patientFound = service.getOne(id);
        if (patientFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(patientFound);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Patient patient) {
        Patient patientFound = service.findByCpf(patient.getCpf());
        if (patientFound != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        service.save(patient);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Patient patient) {
        Patient patientFound = service.getOne(id);
        if (patientFound == null) {
            return ResponseEntity.notFound().build();
        }
        service.save(patient);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Patient patientFound = service.getOne(id);
        if (patientFound == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(patientFound);
        return ResponseEntity.ok().build();
    }
}