package springboot.backend.services;

import org.apache.tomcat.jni.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.backend.models.HospitalBed;
import springboot.backend.models.Medicine;
import springboot.backend.models.Patient;
import springboot.backend.repositories.HospitalBedRepository;
import springboot.backend.repositories.MedicineRepository;
import springboot.backend.repositories.PatientRepository;
import springboot.backend.utils.CustomMethods;
import springboot.backend.utils.ErrorMessenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalBedService {
    private final HospitalBedRepository bedRepository;
    private final PatientRepository patientRepository;
    private final MedicineRepository medicineRepository;

    public HospitalBedService(HospitalBedRepository bedRepository, PatientRepository patientRepository, MedicineRepository medicineRepository) {
        this.bedRepository = bedRepository;
        this.patientRepository = patientRepository;
        this.medicineRepository = medicineRepository;
    }

    public List<HospitalBed> getAll() {
        return bedRepository.findAll();
    }

    public HospitalBed save(HospitalBed hospitalBed) throws Exception {
        Patient patient = hospitalBed.getPatient();

        // se o paciente já existir em outro leito não será possível salvar o leito com esse paciente
        if (isThePatientInAnotherBed(patient)) {
            throw new Exception(ErrorMessenger.PATIENT_IN_ANOTHER_BED);
        }

        // salvar primeiro o paciente e seus medicamentos
        // logo após setar no leito e salvá-lo
        ArrayList<Medicine> bedPatientMedicines = new ArrayList<>();
        Medicine medicineSaved;
        for (Medicine med : patient.getMedicines()) {
            medicineSaved = medicineRepository.save(med);
            bedPatientMedicines.add(medicineSaved);
        }
        patient.setMedicines(bedPatientMedicines);
        patientRepository.save(patient);

        return bedRepository.save(hospitalBed);
    }

    public HospitalBed getOne(Integer id) {
        return bedRepository.findById(id).orElse(null);
    }

    public Boolean didAlreadyExists(Integer id) {
        Optional<HospitalBed> bedFound = bedRepository.findById(id);
        return bedFound.isPresent();
    }

    private Boolean isThePatientInAnotherBed(Patient patient) {
        Patient patientFound = patientRepository.findByCpf(patient.getCpf()).orElse(null);
        if (patientFound == null) {
            return false;
        }
        // verifica se paciente já está em um leito
        for (HospitalBed bed : bedRepository.findAll()) {
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
