package springboot.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.backend.models.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    Medicine findByTitle(String title);
}
