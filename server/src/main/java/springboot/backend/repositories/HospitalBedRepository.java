package springboot.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.backend.models.HospitalBed;

public interface HospitalBedRepository extends JpaRepository<HospitalBed, Integer> {

}
