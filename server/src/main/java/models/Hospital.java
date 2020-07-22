package models;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "hospital")
    List<HospitalBed> hospitalBeds;
}
