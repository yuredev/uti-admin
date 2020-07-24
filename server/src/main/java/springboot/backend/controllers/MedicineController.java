package springboot.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.backend.models.Medicine;
import springboot.backend.services.MedicineService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicines")
public class MedicineController {

    private MedicineService service;

    @Autowired
    public void setService(MedicineService service) {
        this.service = service;
    }

    @GetMapping
    public List<Medicine> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Medicine> getOne(@PathVariable Integer id) {
        Medicine medicineFound = service.getOne(id);
        if (medicineFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(medicineFound);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Medicine medicine) {
        try {
            if (service.alreadyExists(medicine)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            service.save(medicine);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Medicine medicine) {
        Medicine medicineFound = service.getOne(id);
        if (medicineFound == null) {
            return ResponseEntity.notFound().build();
        }
        service.save(medicine);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Medicine medicineFound = service.getOne(id);
        if (medicineFound == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(medicineFound);
        return ResponseEntity.ok().build();
    }
}
