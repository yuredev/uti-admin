package springboot.backend.models;

import com.sun.istack.NotNull;
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
    private Long cpf;

    private String name;
    private Long phone;
    private Byte age;

//    @OneToOne(mappedBy = "patient")
//    HospitalBed hospitalBed;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "patient_medicine",
            joinColumns = @JoinColumn(name = "patient_cpf", referencedColumnName = "cpf"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    List<Medicine> medicines;
}