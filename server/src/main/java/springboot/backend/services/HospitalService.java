package springboot.backend.services;

import org.springframework.stereotype.Service;
import springboot.backend.models.Hospital;
import springboot.backend.repositories.HospitalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<Hospital> getAll() {
        return hospitalRepository.findAll();
    }

    public Hospital getOne(Integer id) {
        return hospitalRepository.findById(id).orElse(null);
    }

    public Hospital save(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public boolean didAlreadyExists(Integer id) {
        Optional<Hospital> hospitalFound = hospitalRepository.findById(id);
        return hospitalFound.isPresent();
    }

    public void delete(Hospital hospital) {
        hospitalRepository.delete(hospital);
    }
}
