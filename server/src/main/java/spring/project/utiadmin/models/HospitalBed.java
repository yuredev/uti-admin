package spring.project.utiadmin.models;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
public class HospitalBed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    Hospital hospital;
}
