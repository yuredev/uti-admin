package springboot.backend.controllers;

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
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> list() {
        return patientService.getAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Patient> getByCpf(@PathVariable Integer id) {
        Patient patientFound = patientService.getOne(id);
        if (patientFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(patientFound);
    }

    @PostMapping
    public ResponseEntity<Patient> insert(@RequestBody Patient patient) {
        if (patientService.didAlreadyExists(patient.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Patient patientSaved = patientService.save(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientSaved);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Patient> update(@PathVariable Integer id, @RequestBody Patient patient) {
        // if anyone patient owns the id of the path variable
        if (!patientService.didAlreadyExists(id)) {
            return ResponseEntity.notFound().build();
        }
        Patient patientSaved = patientService.save(patient);
        return ResponseEntity.ok().body(patientSaved);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Patient patientFound = patientService.getOne(id);
        if (patientFound == null) {
            return ResponseEntity.notFound().build();
        }
        patientService.delete(patientFound);
        return ResponseEntity.ok().build();
    }
}