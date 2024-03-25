package BeerTapDispenserAPI.service;

import BeerTapDispenserAPI.domain.enums.Status;
import BeerTapDispenserAPI.entity.Dispenser;
import BeerTapDispenserAPI.entity.DispenserHistory;
import BeerTapDispenserAPI.repositoy.DispenserHistoryRepo;
import BeerTapDispenserAPI.repositoy.DispenserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DispenserServiceImpl implements DispenserService{

    @Autowired
    DispenserRepo dispenserRepo;
    @Autowired
    DispenserHistoryRepo dispenserHistoryRepo;

    @Override
    public Dispenser createDispenser(double flow_volume){
        return dispenserRepo.save(new Dispenser(flow_volume));
    }

    @Override
    public double getMoneySpent(UUID id) {
        double moneySpent= 0;

        Optional<Dispenser> dispenser = dispenserRepo.findById(id);
        if(dispenser.isPresent()) {
            Optional<List<DispenserHistory>> dispenserHistories = dispenserHistoryRepo.findAllByDispenserId(id);
            if(dispenserHistories.isPresent()){
                List<DispenserHistory> actualDispenserHistories = dispenserHistories.get();
                for (DispenserHistory history : actualDispenserHistories) {
                    moneySpent += history.getTotalAmount();
                }
            }
        }
        return moneySpent;
    }

    @Override
    public int changeStatus(UUID id, Status status, LocalDateTime updatedAt) {
        Optional<Dispenser> existingDispenser = dispenserRepo.findById(id);
        int httpCode;
        if(existingDispenser.isPresent()){
            Dispenser newDispenser = existingDispenser.get();
            if(status.toString().equalsIgnoreCase(newDispenser.getStatus().toString())){
                httpCode = 409;
            }else{
                newDispenser.setStatus(status);
                DispenserHistory history = new DispenserHistory(newDispenser);
                if(status.toString().equalsIgnoreCase("open") ){
                    history.setOpenedAt(updatedAt);
                }if(status.toString().equalsIgnoreCase("closed")){
                    Optional<DispenserHistory> existingHistory = dispenserHistoryRepo.findTopByClosedAtIsNullOrderByOpenedAtDesc();
                    if(existingHistory.isPresent()){
                        history = existingHistory.get();
                        history.setClosedAt(updatedAt);
                        history.setTotalSpent(history.getTotalSpent());
                    }
                }
                dispenserRepo.save(newDispenser);
                dispenserHistoryRepo.save(history);
                httpCode = 202;
            }
        }else{
            httpCode = 404;
        }
        return httpCode;
    }


}
