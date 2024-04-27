package it.gala.test.fabrick.client.feign.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountBalance {

    private LocalDate date;
    private BigDecimal balance;
    private BigDecimal availableBalance;
    private String currency;

}