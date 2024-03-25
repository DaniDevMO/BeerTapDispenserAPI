package BeerTapDispenserAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDispenserResponse {

    private double flowVolume;
    private UUID id;

}
