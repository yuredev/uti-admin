package springboot.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Patient {
    @Id
    @Column(nullable = false, unique = true)
    Long cpf;

    String name;
    Long phone;
    Byte age;

//    @OneToOne(mappedBy = "patient")
//    HospitalBed hospitalBed;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "patient_medicine",
            joinColumns = @JoinColumn(name = "patient_cpf", referencedColumnName = "cpf"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    List<Medicine> medicines;
}