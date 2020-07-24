package springboot.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.backend.models.Patient;
import springboot.backend.repositories.PatientRepository;

import java.util.List;

@Service
public class PatientService {
    private PatientRepository repository;

    @Autowired
    public void setRepository(PatientRepository repository) {
        this.repository = repository;
    }

    public void save(Patient patient) {
        repository.save(patient);
    }

    public void delete(Patient patient) {
        repository.delete(patient);
    }

    public Patient getByCpf(Long cpf) {
        return repository.findByCpf(cpf).orElse(null);
    }

    public List<Patient> getAll() {
        return repository.findAll();
    }
}
