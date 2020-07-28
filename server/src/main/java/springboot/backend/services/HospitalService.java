package springboot.backend.services;

import org.springframework.stereotype.Service;
import springboot.backend.models.Hospital;
import springboot.backend.models.HospitalBed;
import springboot.backend.repositories.HospitalRepository;
import springboot.backend.utils.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    private final HospitalBedService bedService;

    public HospitalService(HospitalRepository hospitalRepository, HospitalBedService bedService) {
        this.hospitalRepository = hospitalRepository;
        this.bedService = bedService;
    }

    public List<Hospital> getAll() {
        return hospitalRepository.findAll();
    }

    public Hospital getOne(Integer id) {
        return hospitalRepository.findById(id).orElse(null);
    }

    private boolean haveEqualBeds(ArrayList<HospitalBed> beds) {
        for (int i = 0; i < beds.size(); i++) {
            for (int j = i + 1; j < beds.size(); j++) {
                if (beds.get(i).getPatient().equals(beds.get(j).getPatient())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Hospital save(Hospital hospital) throws Exception{
        List<HospitalBed> beds = hospital.getHospitalBeds();

        ArrayList<HospitalBed> temporaryBeds = new ArrayList<>(beds);

        if (this.haveEqualBeds(temporaryBeds)) {
            throw new Exception(Message.PATIENT_IN_ANOTHER_BED);
        }

        // salvar todas os leitos antes de salvar o hospital
        for (HospitalBed bed: beds) {
            bedService.save(bed);
        }
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
