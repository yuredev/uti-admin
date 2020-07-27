package springboot.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.backend.models.Hospital;
import springboot.backend.services.HospitalService;
import springboot.backend.utils.ErrorMessenger;
import springboot.backend.utils.ResponseError;

import java.util.List;

@RestController
@RequestMapping("/hospital")
@CrossOrigin(origins = {"http://localhost:3000"}, exposedHeaders = "X-Total-Count")
public class HospitalController {
    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public List<Hospital> list() {
        return hospitalService.getAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Hospital> getOne(@PathVariable Integer id) {
        Hospital hospitalFound = hospitalService.getOne(id);
        if (hospitalFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hospitalFound);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Hospital hospital) {
        try {
            Hospital hospitalCreated = hospitalService.save(hospital);
            return ResponseEntity.status(HttpStatus.CREATED).body(hospitalCreated);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessenger.PATIENT_IN_ANOTHER_BED)) {
                ResponseError resError = new ResponseError(e.getMessage());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(resError);
            } else {
                System.out.println(e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Hospital hospital) {
        if (!hospitalService.didAlreadyExists(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            Hospital hospitalUpdated = hospitalService.save(hospital);
            return ResponseEntity.status(HttpStatus.CREATED).body(hospitalUpdated);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessenger.PATIENT_IN_ANOTHER_BED)) {
                ResponseError resError = new ResponseError(e.getMessage());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(resError);
            } else {
                System.out.println(e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }
}
