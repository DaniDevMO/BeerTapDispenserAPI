package BeerTapDispenserAPI.service;

import BeerTapDispenserAPI.entity.DispenserHistory;

import java.util.List;
import java.util.UUID;

public interface DispenserHistoryService {

    public List<DispenserHistory> getDispenserHistoryByDispenserId(UUID id);

}
