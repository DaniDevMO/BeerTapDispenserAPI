package BeerTapDispenserAPI.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Entity
@Data
public class DispenserHistory {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "dispenser_id", nullable = false)
    private Dispenser dispenser;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime openedAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonProperty("closed_at")
    private LocalDateTime closedAt;

    private double totalSpent;

    public DispenserHistory(){}

    public DispenserHistory(Dispenser dispenser){
        this.dispenser = dispenser;
    }

    public double getTotalAmount(){
         double result = 0;
         Duration duration = Duration.between(this.getOpenedAt(), this.getClosedAt());
         double minutesOpened = duration.toMinutes();
         result = this.dispenser.getFlow_volume() * this.dispenser.getPrice_per_litter() * (minutesOpened / 60);

         return result;
    }

}
