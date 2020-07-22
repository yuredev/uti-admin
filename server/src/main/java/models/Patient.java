package models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cpf;
    private String name;
    private Long phone;
    private Byte age;

    @OneToOne(mappedBy = "patient")
    HospitalBed hospitalBed;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "patient_medicine",
            joinColumns = @JoinColumn(name = "patient_cpf", referencedColumnName = "cpf"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    List<Medicine> medicines;
}