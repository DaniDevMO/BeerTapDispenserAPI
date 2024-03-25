package BeerTapDispenserAPI.service;

import BeerTapDispenserAPI.domain.enums.Status;
import BeerTapDispenserAPI.entity.Dispenser;
import BeerTapDispenserAPI.entity.DispenserHistory;
import BeerTapDispenserAPI.repositoy.DispenserHistoryRepo;
import BeerTapDispenserAPI.repositoy.DispenserRepo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
public class DispenserServiceTests {

    @Autowired
    DispenserRepo dispenserRepoTest;
    @Autowired
    DispenserHistoryRepo historyRepoTest;
    @Autowired
    DispenserServiceImpl dispenserService;

    Dispenser dispenserMock;
    DispenserHistory historyMock;
    DispenserHistory historyMock2;


    @BeforeEach
    void init(){
        dispenserMock = new Dispenser(10.0);
        historyMock = new DispenserHistory(dispenserMock);
        historyMock2 = new DispenserHistory(dispenserMock);
        historyMock.setOpenedAt(LocalDateTime.of(2024, 3, 19, 12, 30));
        historyMock.setClosedAt(LocalDateTime.of(2024, 3, 19, 16, 30));
        historyMock2.setOpenedAt(LocalDateTime.of(2024, 3, 20, 12, 30));
        historyMock2.setClosedAt(LocalDateTime.of(2024, 3, 20, 16, 30));
        dispenserRepoTest.save(dispenserMock);
        historyRepoTest.save(historyMock);
        historyRepoTest.save(historyMock2);
    }

    @Test
    void createDispenser(){
        UUID expected = dispenserService.createDispenser(10.0).getId();
        assertNotNull(expected);
    }

    @Test
    void getMoneySpent(){
        double expectedAmount = 980.0;
        assertEquals(dispenserService.getMoneySpent(dispenserMock.getId()), expectedAmount);
    }

    @Test
    void changeStatusToOpen(){
        Status expected = Status.open;
        dispenserService.changeStatus(dispenserMock.getId(), Status.open, LocalDateTime.of(2024, 3, 19, 12, 30));
        Dispenser finalDispenser = dispenserRepoTest.findById(dispenserMock.getId()).get();
        assertEquals(expected, finalDispenser.getStatus());
    }

    @Test
    void changeStatusToClosed(){
        Status expected = Status.closed;
        dispenserService.changeStatus(dispenserMock.getId(), Status.closed, LocalDateTime.of(2024, 3, 19, 12, 30));
        Dispenser finalDispenser = dispenserRepoTest.findById(dispenserMock.getId()).get();
        assertEquals(expected, finalDispenser.getStatus());
    }

}
