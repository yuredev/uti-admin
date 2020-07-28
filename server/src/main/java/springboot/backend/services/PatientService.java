package springboot.backend.services;

import org.springframework.stereotype.Service;
import springboot.backend.models.HospitalBed;
import springboot.backend.models.Medicine;
import springboot.backend.models.Patient;
import springboot.backend.repositories.HospitalBedRepository;
import springboot.backend.repositories.PatientRepository;
import springboot.backend.utils.CustomMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final MedicineService medicineService;
    private final HospitalBedRepository bedRepository;

    public PatientService(PatientRepository patientRepository, MedicineService medicineService, HospitalBedRepository bedRepository) {
        this.patientRepository = patientRepository;
        this.medicineService = medicineService;
        this.bedRepository = bedRepository;
    }

    public Patient save(Patient patient) {
        // make the first letter of patient's name uppercase
        patient.setName(CustomMethods.firstLetterUpper(patient.getName()));

        // save the medicines before save the patient
        // because CascadeType.ALL in Patient class is missing
        ArrayList<Medicine> patientMedicines = new ArrayList<>();
        Medicine medicineSaved;
        if (patient.getMedicines() != null) {
            for (Medicine med : patient.getMedicines()) {
                medicineSaved = medicineService.save(med);
                patientMedicines.add(medicineSaved);
            }
        }
        patient.setMedicines(patientMedicines);
        return patientRepository.save(patient);
        // -------------

    }

    public void delete(Patient patient) {
        // remove the patient from the hospital bed that he is using before delete
        List<HospitalBed> hospitalBeds = bedRepository.findAll();
        for (HospitalBed bed : hospitalBeds) {
            if (bed.getPatient() != null) {
                if (bed.getPatient().equals(patient)) {
                    bed.setPatient(null);
                    bedRepository.save(bed);
                    break;
                }
            }
        }
        patientRepository.delete(patient);
    }

    public Boolean didAlreadyExists(Long cpf) {
        Optional<Patient> patientFound = patientRepository.findByCpf(cpf);
        return patientFound.isPresent();
    }

    public Boolean didAlreadyExists(Integer id) {
        Optional<Patient> patientFound = patientRepository.findById(id);
        return patientFound.isPresent();
    }

    public Patient getOne(Integer id) {
        return patientRepository.findById(id).orElse(null);
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }
}
