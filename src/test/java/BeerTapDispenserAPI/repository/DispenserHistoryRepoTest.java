package BeerTapDispenserAPI.repository;

import BeerTapDispenserAPI.entity.Dispenser;
import BeerTapDispenserAPI.entity.DispenserHistory;
import BeerTapDispenserAPI.repositoy.DispenserHistoryRepo;
import BeerTapDispenserAPI.repositoy.DispenserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class DispenserHistoryRepoTest {

    @Autowired
    private DispenserHistoryRepo historyRepoTest;
    @Autowired
    private DispenserRepo dispenserRepoTest;

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
    void findAllByDispenserIdTest(){
        List<DispenserHistory> expectedResult = new ArrayList<>();
        expectedResult.add(historyMock);
        expectedResult.add(historyMock2);
        assertEquals(historyRepoTest.findAllByDispenserId(dispenserMock.getId()).get(),expectedResult);
    }

    @Test
    void findTopByClosedAtIsNullOrderByOpenedAtDesc(){
            DispenserHistory expectedResult = historyMock2;
            List<DispenserHistory> historyList = new ArrayList<>();
            historyList.add(historyMock);
            historyList.add(historyMock2);
            assertEquals(historyRepoTest.findTopByClosedAtIsNullOrderByOpenedAtDesc().get(),expectedResult);
    }
}
