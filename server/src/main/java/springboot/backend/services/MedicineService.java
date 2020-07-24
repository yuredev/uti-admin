package springboot.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.backend.models.Medicine;
import springboot.backend.repositories.MedicineRepository;

import java.util.List;

@Service
public class MedicineService {
    private MedicineRepository repository;

    @Autowired
    public void setRepository(MedicineRepository repository) {
        this.repository = repository;
    }

    public void save(Medicine medicine) {
        repository.save(medicine);
    }

    public List<Medicine> getAll() {
        return repository.findAll();
    }

    public Boolean alreadyExists(Medicine medicine) {
        return repository.findByTitle(medicine.getTitle()) != null;
    }

    public Medicine getOne(Integer id) {
        return repository.findById(id).orElse(null);
    }
    public void delete(Medicine medicine) {
        repository.delete(medicine);
    }
}
