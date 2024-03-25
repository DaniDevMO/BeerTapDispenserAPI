/*package BeerTapDispenserAPI.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;
import java.util.UUID;

import BeerTapDispenserAPI.BeerTapDispenserApiApplication;
import BeerTapDispenserAPI.domain.enums.Status;
import BeerTapDispenserAPI.service.DispenserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import BeerTapDispenserAPI.controller.DispenserController;
import BeerTapDispenserAPI.domain.CreateDispenserResponse;
import BeerTapDispenserAPI.domain.ExpensesResponse;
import BeerTapDispenserAPI.dto.ChangeStatusBodyDTO;
import BeerTapDispenserAPI.entity.Dispenser;
import BeerTapDispenserAPI.service.DispenserHistoryServiceImpl;
import BeerTapDispenserAPI.service.DispenserServiceImpl;
import BeerTapDispenserAPI.service.mapper.DispenserHistoryToExpensesResponseMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(controllers = BeerTapDispenserAPI.controller.DispenserController.class )
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class DispenserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DispenserServiceImpl dispenserService;

    private Dispenser dispenser;

    @BeforeEach
    public void init(){
        dispenser = new Dispenser(10.0);
    }

    @Test
    public void testCreateDispenser()throws Exception {

        given(dispenserService.createDispenser(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post(""))
                .contentType(MediaType.APPLICATION_JSON)
                .content(ob);
    }


    @Test
    public void testChangeStatus() {

        ChangeStatusBodyDTO changeStatusBodyDTO = new ChangeStatusBodyDTO();
        changeStatusBodyDTO.setStatus(Status.open);
        changeStatusBodyDTO.setUpdatedAt(LocalDateTime.of(2024, 3, 18, 12, 0, 0));

        UUID id = UUID.randomUUID();
        when(dispenserService.changeStatus(id, Status.closed, LocalDateTime.of(2024, 3, 18, 12, 0, 0))).thenReturn(200);

        ResponseEntity<Object> response = controller.changeStatus(changeStatusBodyDTO, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetSpendings() {
        DispenserHistoryServiceImpl dispenserHistoryService = mock(DispenserHistoryServiceImpl.class);
        DispenserHistoryToExpensesResponseMapper historyMapper = mock(DispenserHistoryToExpensesResponseMapper.class);
        DispenserController controller = new DispenserController();
        controller.setDispenserHistoryService(dispenserHistoryService);
        controller.setHistoryMapper(historyMapper);

        UUID id = UUID.randomUUID();

        ExpensesResponse expectedResponse = new ExpensesResponse();
        when(historyMapper.mapToExpensesResponse(any())).thenReturn(expectedResponse);

        ResponseEntity<ExpensesResponse> response = controller.getSpendings(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }
}*/