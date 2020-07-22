package spring.project.utiadmin.models;

import javax.persistence.*;

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
}
