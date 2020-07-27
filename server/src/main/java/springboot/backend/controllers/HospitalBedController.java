package springboot.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.backend.models.HospitalBed;
import springboot.backend.services.HospitalBedService;
import springboot.backend.utils.ErrorMessenger;

import java.util.List;

@RestController
@RequestMapping("/hospital-beds")
@CrossOrigin(origins = {"http://localhost:3000"}, exposedHeaders = "X-Total-Count")
public class HospitalBedController {
    private HospitalBedService service;

    @Autowired
    public void setService(HospitalBedService service) {
        this.service = service;
    }

    @RequestMapping
    public List<HospitalBed> list() {
        return service.getAll();
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<HospitalBed> getOne(@PathVariable Integer id) {
        HospitalBed bedFound = service.getOne(id);
        if (bedFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(bedFound);
    }

    @PostMapping
    public ResponseEntity<HospitalBed> insert(@RequestBody HospitalBed bed) {
        try {
            HospitalBed bedCreated = service.save(bed);
            return ResponseEntity.status(HttpStatus.CREATED).body(bedCreated);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessenger.CPF_ALREADY_IN_USE)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {
                System.out.println(e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<HospitalBed> update(@PathVariable Integer id, @RequestBody HospitalBed bed) {
        if (!service.didAlreadyExists(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            HospitalBed bedCreated = service.save(bed);
            return ResponseEntity.status(HttpStatus.CREATED).body(bedCreated);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessenger.CPF_ALREADY_IN_USE)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {
                System.out.println(e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        HospitalBed bedFound = service.getOne(id);
        if (bedFound == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(bedFound);
        return ResponseEntity.ok().build();
    }
}
