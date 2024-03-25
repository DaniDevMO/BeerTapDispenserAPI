package BeerTapDispenserAPI.entity;

import BeerTapDispenserAPI.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Data
@Table(name = "dispensers")
public class Dispenser {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Status status = Status.closed;

    private double flow_volume;

    private double price_per_litter = 12.25;

    public Dispenser(double flow_volume){
        this.flow_volume = flow_volume;
    }

}
