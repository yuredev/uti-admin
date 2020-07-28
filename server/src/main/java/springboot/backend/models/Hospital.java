package springboot.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import springboot.backend.utils.Message;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Hospital {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = Message.BLANK)
    @NotNull(message = Message.NULL)
    @Size(min = 2, max = 70, message = Message.OUT_OF_SIZE_NAME)
    private String name;

    @JoinColumn(name = "hospital_id")
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    List<HospitalBed> hospitalBeds;
}
