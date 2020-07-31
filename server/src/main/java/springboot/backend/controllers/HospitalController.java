package springboot.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.backend.models.Hospital;
import springboot.backend.services.HospitalService;

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
    public ResponseEntity<Hospital> insert(@RequestBody Hospital hospital) {
        Hospital hospitalCreated = hospitalService.save(hospital);
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalCreated);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Hospital> update(@PathVariable Integer id, @RequestBody Hospital hospital) {
        if (!hospitalService.didAlreadyExists(id)) {
            return ResponseEntity.notFound().build();
        }
        Hospital hospitalUpdated = hospitalService.save(hospital);
        return ResponseEntity.ok(hospitalUpdated);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Hospital hospitalFound = hospitalService.getOne(id);
        var responseMsg = new HashMap<>();
        responseMsg.put("message", "The hospital was successfully deleted");
        if (hospitalFound == null) {
            return ResponseEntity.notFound().build();
        }
        hospitalService.delete(hospitalFound);
        return ResponseEntity.ok().body(responseMsg);
    }
}
