package springboot.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.backend.models.HospitalBed;
import springboot.backend.services.HospitalBedService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/hospital-beds")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8081"}, exposedHeaders = "X-Total-Count")
public class HospitalBedController {
    private HospitalBedService service;

    @Autowired
    public void setService(HospitalBedService service) {
        this.service = service;
    }

    @GetMapping
    public List<HospitalBed> list() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<HospitalBed> getOne(@PathVariable Integer id) {
        HospitalBed bedFound = service.getOne(id);
        if (bedFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bedFound);
    }

    @PostMapping
    public ResponseEntity<HospitalBed> insert(@RequestBody HospitalBed bed) {
        if (bed.getPatient() != null) 
        	System.out.println(bed.getPatient().getName());
        HospitalBed bedCreated = service.save(bed);
        return ResponseEntity.status(HttpStatus.CREATED).body(bedCreated);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<HospitalBed> update(@PathVariable Integer id, @RequestBody HospitalBed bed) {
        if (!service.didAlreadyExists(id)) {
            return ResponseEntity.notFound().build();
        }
        HospitalBed bedUpdated = service.save(bed);
        return ResponseEntity.ok(bedUpdated);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        HospitalBed bedFound = service.getOne(id);
        var responseMsg = new HashMap<>();
        responseMsg.put("message", "The bed was successfully deleted");
        if (bedFound == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(bedFound);
        return ResponseEntity.ok().body(responseMsg);
    }
}
