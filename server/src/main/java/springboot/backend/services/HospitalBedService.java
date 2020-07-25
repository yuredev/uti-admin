package springboot.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.backend.models.HospitalBed;
import springboot.backend.repositories.HospitalBedRepository;

import java.util.List;

@Service
public class HospitalBedService {
    private HospitalBedRepository repository;

    @Autowired
    public void setRepository(HospitalBedRepository repository) {
        this.repository = repository;
    }

    public List<HospitalBed> getAll() {
        return repository.findAll();
    }

    public void save(HospitalBed hospitalBed) {
        repository.save(hospitalBed);
    }

}
