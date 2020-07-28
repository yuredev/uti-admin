package springboot.backend.services;

import org.springframework.stereotype.Service;
import springboot.backend.models.HospitalBed;
import springboot.backend.models.Patient;
import springboot.backend.repositories.HospitalBedRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalBedService {
    private final HospitalBedRepository bedRepository;
    private final PatientService patientService;

    public HospitalBedService(HospitalBedRepository hbs, PatientService ps) {
        this.bedRepository = hbs;
        this.patientService = ps;
    }

    public List<HospitalBed> getAll() {
        return bedRepository.findAll();
    }

    public HospitalBed save(HospitalBed hospitalBed) {
        Patient patient = hospitalBed.getPatient();
        patientService.save(patient);
        return bedRepository.save(hospitalBed);
    }

    public HospitalBed getOne(Integer id) {
        return bedRepository.findById(id).orElse(null);
    }

    public boolean didAlreadyExists(Integer id) {
        Optional<HospitalBed> bedFound = bedRepository.findById(id);
        return bedFound.isPresent();
    }

    public void delete(HospitalBed hospitalBed) {
        patientService.delete(hospitalBed.getPatient());
        bedRepository.delete(hospitalBed);
    }
}
