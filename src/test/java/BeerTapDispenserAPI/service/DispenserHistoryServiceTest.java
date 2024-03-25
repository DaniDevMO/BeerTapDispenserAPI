package BeerTapDispenserAPI.service;

import BeerTapDispenserAPI.entity.Dispenser;
import BeerTapDispenserAPI.entity.DispenserHistory;
import BeerTapDispenserAPI.repositoy.DispenserHistoryRepo;
import BeerTapDispenserAPI.repositoy.DispenserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DispenserHistoryServiceTest {

    @Autowired
    DispenserRepo dispenserRepoTest;
    @Autowired
    DispenserHistoryRepo historyRepoTest;
    @Autowired
    DispenserHistoryServiceImpl dispenserHistoryService;

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
        historyMock.setOpenedAt(LocalDateTime.of(2024, 3, 20, 12, 30));
        historyMock.setClosedAt(LocalDateTime.of(2024, 3, 20, 16, 30));
        dispenserRepoTest.save(dispenserMock);
        historyRepoTest.save(historyMock);
        historyRepoTest.save(historyMock2);
    }


    @Test
    void getDispenserHistoryByDispenserId(){
        List<DispenserHistory> expectedResult = new ArrayList<>();
        expectedResult.add(historyMock);
        expectedResult.add(historyMock2);
        assertEquals(dispenserHistoryService.getDispenserHistoryByDispenserId(dispenserMock.getId()),expectedResult);
    }


}
