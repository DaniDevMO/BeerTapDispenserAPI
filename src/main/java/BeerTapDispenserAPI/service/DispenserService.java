package BeerTapDispenserAPI.service;

import BeerTapDispenserAPI.domain.enums.Status;
import BeerTapDispenserAPI.entity.Dispenser;

import java.time.LocalDateTime;
import java.util.UUID;

public interface DispenserService {

    public Dispenser createDispenser(double flowVolume);
    public double getMoneySpent(UUID id);
    public int changeStatus(UUID id, Status status, LocalDateTime updatedAt);





}
