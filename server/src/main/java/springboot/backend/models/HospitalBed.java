package springboot.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class HospitalBed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @JoinColumn
    @ManyToOne
    Hospital hospital;

    @OneToOne
    @JoinColumn(name = "patient_cpf")
    Patient patient;
}
