package springboot.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.backend.models.Medicine;
import springboot.backend.models.Patient;
import springboot.backend.services.MedicineService;
import springboot.backend.services.PatientService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = {"http://localhost:3000"}, exposedHeaders = "X-Total-Count")
public class PatientController {
    private final PatientService service;
    private final MedicineService medService;

    public PatientController(PatientService service, MedicineService medService) {
        this.service = service;
        this.medService = medService;
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
    public ResponseEntity<Patient> insert(@RequestBody Patient patient) {
        Patient patientFound = service.findByCpf(patient.getCpf());
        if (patientFound != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        // CASCADE
        ArrayList<Medicine> patientMedicines = new ArrayList<>();
        Medicine medicineSaved;

        for (Medicine med: patient.getMedicines()) {
            medicineSaved = medService.save(med);
            patientMedicines.add(medicineSaved);
        }
        patient.setMedicines(patientMedicines);
        // -------------


        Patient patientSaved = service.save(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientSaved);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Patient> update(@PathVariable Integer id, @RequestBody Patient patient) {
        Patient patientFound = service.getOne(id);
        if (patientFound == null) {
            return ResponseEntity.notFound().build();
        }
        Patient patientSaved = service.save(patient);
        return ResponseEntity.ok().body(patientSaved);
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