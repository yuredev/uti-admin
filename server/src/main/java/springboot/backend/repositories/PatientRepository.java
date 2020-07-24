package springboot.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.backend.models.Patient;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByCpf(Long cpf);
}
