package springboot.backend.services;

import org.springframework.stereotype.Service;
import springboot.backend.models.HospitalBed;
import springboot.backend.models.Patient;
import springboot.backend.repositories.HospitalBedRepository;
import springboot.backend.utils.Message;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalBedService {
    private final HospitalBedRepository bedRepository;
    private final PatientService patientService;

    public HospitalBedService(HospitalBedRepository bedRepository, PatientService patientService) {
        this.bedRepository = bedRepository;
        this.patientService = patientService;
    }

    public List<HospitalBed> getAll() {
        return bedRepository.findAll();
    }

    public HospitalBed save(HospitalBed hospitalBed) throws Exception {
        Patient patient = hospitalBed.getPatient();
        // se o paciente já existir em outro leito não será possível salvar o leito com esse paciente
        if (isThePatientInAnotherBed(patient)) {
            throw new Exception(Message.PATIENT_IN_ANOTHER_BED);
        }
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

    private Boolean isThePatientInAnotherBed(Patient patient) {
        Patient patientFound = patientService.getOne(patient.getCpf());
        // se o paciente não existir não há como ele estar em um leito
        if (patientFound == null) {
            return false;
        }
        // percorrer leitos existentes
        for (HospitalBed bed : bedRepository.findAll()) {
            // verificar se o paciente está no leito
            if (bed.getPatient().equals(patientFound)) {
                return true;
            }
        }
        return false;
    }

    public void delete(HospitalBed hospitalBed) {
        bedRepository.delete(hospitalBed);
    }
}
