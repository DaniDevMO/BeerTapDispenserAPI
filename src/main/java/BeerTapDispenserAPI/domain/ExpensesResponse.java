package BeerTapDispenserAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesResponse {

    private double amount;
    private List<Usage> usages;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Usage {
        private String opened_at;
        private String closed_at;
        private double flow_volume;
        private double total_spent;
    }

}
