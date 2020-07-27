package springboot.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.backend.models.HospitalBed;
import springboot.backend.services.HospitalBedService;
import springboot.backend.utils.Message;

import java.util.HashMap;
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
    public ResponseEntity<?> save(@RequestBody HospitalBed bed) {
        try {
            HospitalBed bedCreated = service.save(bed);
            return ResponseEntity.status(HttpStatus.CREATED).body(bedCreated);
        } catch (Exception e) {
            if (e.getMessage().equals(Message.PATIENT_IN_ANOTHER_BED)) {
                HashMap<String, String> resBody = new HashMap<>();
                resBody.put("error", Message.PATIENT_IN_ANOTHER_BED);
                resBody.put("message", Message.PATIENT_IN_ANOTHER_BED_DESCRIPTION);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(resBody);
            } else {
                System.out.println(e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody HospitalBed bed) {
        if (!service.didAlreadyExists(id)) {
            return ResponseEntity.notFound().build();
        }
        return this.save(bed);
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
