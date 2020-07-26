package springboot.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springboot.backend.utils.ErrorMessenger;

import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = ErrorMessenger.NULL)
    @Positive(message = ErrorMessenger.NEGATIVE)
    @Column(nullable = false, unique = true)
    private Long cpf;

    @NotBlank(message = ErrorMessenger.BLANK)
    @NotNull(message = ErrorMessenger.NULL)
    @Size(min = 2, max = 70, message = ErrorMessenger.OUT_OF_SIZE_NAME)
    private String name;

    @NotBlank(message = ErrorMessenger.BLANK)
    @NotNull(message = ErrorMessenger.NULL)
    private String hospitalizationDate;

    @NotBlank(message = ErrorMessenger.BLANK)
    private String observations;

    private Long phone;

    @NotNull(message = ErrorMessenger.NULL)
    @Positive(message = ErrorMessenger.NEGATIVE)
    private Byte age;

//    @OneToOne(mappedBy = "patient")
//    HospitalBed hospitalBed;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "patient_medicine",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    List<Medicine> medicines;
}