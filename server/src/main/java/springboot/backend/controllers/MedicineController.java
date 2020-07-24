package springboot.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.backend.models.Medicine;
import springboot.backend.services.MedicineService;

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

    @PostMapping(path = "/{id}")
    public ResponseEntity<?> insert(@RequestBody Medicine medicine) {
        try {
            Medicine medicineFound = service.getOne(medicine.getId());
            if (medicineFound != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            service.save(medicine);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
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

    @DeleteMapping(path = "{/id}")
    public ResponseEntity<?> delete(@PathVariable Integer id, @RequestBody Medicine medicine) {
        Medicine medicineFound = service.getOne(id);
        if (medicineFound == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(medicine);
        return ResponseEntity.ok().build();
    }
}
