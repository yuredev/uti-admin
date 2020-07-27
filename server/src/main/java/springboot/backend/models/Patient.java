package springboot.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springboot.backend.utils.Message;

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

    @NotNull(message = Message.NULL)
    @Positive(message = Message.NEGATIVE)
    @Column(nullable = false, unique = true)
    private Long cpf;

    @NotBlank(message = Message.BLANK)
    @NotNull(message = Message.NULL)
    @Size(min = 2, max = 70, message = Message.OUT_OF_SIZE_NAME)
    private String name;

    @NotBlank(message = Message.BLANK)
    @NotNull(message = Message.NULL)
    private String hospitalizationDate;

    @NotBlank(message = Message.BLANK)
    private String observations;

    private Long phone;

    @NotNull(message = Message.NULL)
    @Positive(message = Message.NEGATIVE)
    private Byte age;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "patient_medicine",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    List<Medicine> medicines;
}