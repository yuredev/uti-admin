package springboot.backend.models;

import javax.persistence.*;

@Entity
public class HospitalBed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ManyToOne
    Hospital hospital;

    @OneToOne
    @JoinColumn(name = "patient_cpf")
    Patient patient;
}
