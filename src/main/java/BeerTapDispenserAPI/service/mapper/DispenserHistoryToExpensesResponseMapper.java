package BeerTapDispenserAPI.service.mapper;

import BeerTapDispenserAPI.domain.ExpensesResponse;
import BeerTapDispenserAPI.entity.DispenserHistory;
import BeerTapDispenserAPI.service.DispenserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DispenserHistoryToExpensesResponseMapper {

    @Autowired
    private DispenserService dispenserService;

    public ExpensesResponse mapToExpensesResponse(List<DispenserHistory> dispenserHistories) {
        double totalAmount = dispenserService.getMoneySpent(dispenserHistories.get(0).getDispenser().getId());

        List<ExpensesResponse.Usage> usages = dispenserHistories.stream()
                .map(history -> new ExpensesResponse.Usage(
                        history.getOpenedAt().toString(),
                        history.getClosedAt() != null ? history.getClosedAt().toString() : null,
                        history.getDispenser().getFlow_volume(),
                        history.getTotalSpent()
                ))
                .collect(Collectors.toList());

        return new ExpensesResponse(totalAmount, usages);
    }

}
