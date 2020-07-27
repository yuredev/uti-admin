package springboot.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.backend.models.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
