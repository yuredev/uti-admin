package springboot.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.backend.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByCpf(Long cpf);
}
