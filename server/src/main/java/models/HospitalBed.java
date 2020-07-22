package models;

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

    @OneToOne
    @JoinColumn(name = "patient_cpf")
    Patient patient;
}
