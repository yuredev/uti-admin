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
@CrossOrigin(origins = {"http://localhost:3000"}, exposedHeaders = "X-Total-Count")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping
    public List<Medicine> list() {
        return medicineService.getAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Medicine> getOne(@PathVariable Integer id) {
        Medicine medicineFound = medicineService.getOne(id);
        if (medicineFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(medicineFound);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Medicine medicine) {
        if (medicineService.didAlreadyExists(medicine)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Medicine medicineCreated = medicineService.save(medicine);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicineCreated);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Medicine> update(@PathVariable Integer id, @RequestBody Medicine medicine) {
        if (!medicineService.didAlreadyExists(id)) {
            return ResponseEntity.notFound().build();
        }
        Medicine medicineUpdated = medicineService.save(medicine);
        return ResponseEntity.ok().body(medicineUpdated);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Medicine medicineFound = medicineService.getOne(id);
        if (medicineFound == null) {
            return ResponseEntity.notFound().build();
        }
        medicineService.delete(medicineFound);
        return ResponseEntity.ok().build();
    }
}
