package springboot.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.backend.models.Hospital;
import springboot.backend.services.HospitalService;
import springboot.backend.utils.Message;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/hospitals")
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
    public ResponseEntity<?> save(@RequestBody Hospital hospital) throws Exception {
        Hospital hospitalSaved = hospitalService.save(hospital);
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalSaved);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Hospital hospital) throws Exception {
        if (!hospitalService.didAlreadyExists(id)) {
            return ResponseEntity.notFound().build();
        }
        return this.save(hospital);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Hospital hospitalFound = hospitalService.getOne(id);
        if (hospitalFound == null) {
            return ResponseEntity.notFound().build();
        }
        hospitalService.delete(hospitalFound);
        return ResponseEntity.ok().build();
    }
}
