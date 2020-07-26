package springboot.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.backend.models.Medicine;
import springboot.backend.models.Patient;
import springboot.backend.repositories.MedicineRepository;
import springboot.backend.repositories.PatientRepository;
import springboot.backend.utils.CustomMethods;

import java.util.List;

@Service
public class MedicineService {
    private final MedicineRepository medicineRepo;
    private final PatientRepository patientRepo;

    public MedicineService(MedicineRepository medicineRepo, PatientRepository patientRepo) {
        this.medicineRepo = medicineRepo;
        this.patientRepo = patientRepo;
    }

    public Medicine save(Medicine medicine) {
        medicine.setTitle(CustomMethods.firstLetterUpper(medicine.getTitle()));
        return medicineRepo.save(medicine);
    }

    public List<Medicine> getAll() {
        return medicineRepo.findAll();
    }

    public Boolean alreadyExists(Medicine medicine) {
        String medicineTitle = CustomMethods.firstLetterUpper(medicine.getTitle());
        return medicineRepo.findByTitle(medicineTitle) != null;
    }

    public Medicine getOne(Integer id) {
        return medicineRepo.findById(id).orElse(null);
    }

    public void delete(Medicine medicine) {
        List<Patient> patientList = patientRepo.findAll();
        int numberOfMedicines;
        List<Medicine> patientMedicines;

        for (Patient patient : patientList) {
            patientMedicines = patient.getMedicines();
            numberOfMedicines = patientMedicines.size();
            for (int i = 0; i < numberOfMedicines; i++) {
                if (patientMedicines.get(i).equals(medicine)) {
                    patientMedicines.remove(medicine);
                    numberOfMedicines--;
                    patientRepo.save(patient);
                }
            }
        }
        medicineRepo.delete(medicine);
    }
}
