package BeerTapDispenserAPI.controller;

import BeerTapDispenserAPI.domain.CreateDispenserResponse;
import BeerTapDispenserAPI.domain.ExpensesResponse;
import BeerTapDispenserAPI.dto.ChangeStatusBodyDTO;
import BeerTapDispenserAPI.entity.Dispenser;
import BeerTapDispenserAPI.service.DispenserHistoryServiceImpl;
import BeerTapDispenserAPI.service.DispenserServiceImpl;
import BeerTapDispenserAPI.service.mapper.DispenserHistoryToExpensesResponseMapper;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/dispenser")
public class DispenserController {

    @Autowired
    DispenserServiceImpl dispenserService;
    @Autowired
    DispenserHistoryToExpensesResponseMapper historyMapper;
    @Autowired
    DispenserHistoryServiceImpl dispenserHistoryService;

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<CreateDispenserResponse> createDispenser(@RequestBody Dispenser dispenser){
        Dispenser dispenserResponse = dispenserService.createDispenser(dispenser.getFlow_volume());
        return ResponseEntity.ok(new CreateDispenserResponse(dispenserResponse.getFlow_volume(), dispenserResponse.getId()));
    }

    @PutMapping("/{id}/status")
    @ResponseBody
    public ResponseEntity<Object> changeStatus(@RequestBody ChangeStatusBodyDTO changeStatusBodyDTO, @PathParam("id") UUID id ){
        try{
            int response = dispenserService.changeStatus(id, changeStatusBodyDTO.getStatus(), changeStatusBodyDTO.getUpdatedAt());
            return ResponseEntity.status(response).build();
        }catch(Exception e){
            log.error("Error for change status request for id " + id + ": ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected API error");
        }

    }

    @GetMapping("/{id}/spending")
    @ResponseBody
    public ResponseEntity<ExpensesResponse> getSpendings(@PathParam("id")UUID id){

        var result = historyMapper.mapToExpensesResponse(dispenserHistoryService.getDispenserHistoryByDispenserId(id));
        return ResponseEntity.ok(result);
    }

}
