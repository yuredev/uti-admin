package springboot.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.backend.models.HospitalBed;
import springboot.backend.services.HospitalBedService;

import java.util.List;

@RestController
@RequestMapping("/hospital-beds")
@CrossOrigin(origins = {"http://localhost:3000"}, exposedHeaders = "X-Total-Count")
public class HospitalBedController {
    private HospitalBedService service;

    @Autowired
    public void setService(HospitalBedService service) {
        this.service = service;
    }

    @RequestMapping
    public List<HospitalBed> getAll() {
        return service.getAll();
    }
}
