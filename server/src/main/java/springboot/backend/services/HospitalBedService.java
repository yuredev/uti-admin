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
        Patient bedPatient = hospitalBed.getPatient();

        boolean didPatientAlreadyExists = patientRepository.findByCpf(bedPatient.getCpf()).isPresent();
        if (didPatientAlreadyExists) {
            throw new Exception(ErrorMessenger.CPF_ALREADY_IN_USE);
        }

        ArrayList<Medicine> bedPatientMedicines = new ArrayList<>();
        Medicine medicineSaved;

        for (Medicine med : bedPatient.getMedicines()) {
            medicineSaved = medicineRepository.save(med);
            bedPatientMedicines.add(medicineSaved);
        }
        bedPatient.setMedicines(bedPatientMedicines);
        patientRepository.save(bedPatient);

        return bedRepository.save(hospitalBed);
    }

    public HospitalBed getOne(Integer id) {
        return bedRepository.findById(id).orElse(null);
    }

    public Boolean didAlreadyExists(Integer id) {
        Optional<HospitalBed> bedFound = bedRepository.findById(id);
        return bedFound.isPresent();
    }

    public void delete(HospitalBed hospitalBed) {
        bedRepository.delete(hospitalBed);
    }
}
