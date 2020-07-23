package springboot.backend.models;

import org.hibernate.annotations.Fetch;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hospital {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @JoinColumn
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<HospitalBed> hospitalBeds;
}
