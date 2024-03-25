package BeerTapDispenserAPI.service;

import BeerTapDispenserAPI.entity.DispenserHistory;
import BeerTapDispenserAPI.repositoy.DispenserHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class DispenserHistoryServiceImpl implements DispenserHistoryService {
    @Autowired
    DispenserHistoryRepo dispenserHistoryRepo;

    @Override
    public List<DispenserHistory> getDispenserHistoryByDispenserId(UUID id) {
        var optionalHistories = dispenserHistoryRepo.findAllByDispenserId(id);
        return optionalHistories.isPresent() ? optionalHistories.get() : Collections.EMPTY_LIST;
    }
}
