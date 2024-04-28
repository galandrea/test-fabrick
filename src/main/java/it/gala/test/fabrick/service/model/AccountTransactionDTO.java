package it.gala.test.fabrick.service.model;

import it.gala.test.fabrick.client.feign.model.AccountTransactions;
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
public class AccountTransactionDTO {

    private String transactionId;
    private String operationId;
    private LocalDate accountingDate;
    private LocalDate valueDate;
    private AccountTransactions.TransactionType type;
    private BigDecimal amount;
    private String currency;
    private String description;

}
