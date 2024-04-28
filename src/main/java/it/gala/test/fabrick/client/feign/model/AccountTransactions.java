package it.gala.test.fabrick.client.feign.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransactions {

    private List<Transaction> list;

    @Getter
    @Setter
    public static class Transaction {

        private String transactionId;
        private String operationId;
        private LocalDate accountingDate;
        private LocalDate valueDate;
        private TransactionType type;
        private BigDecimal amount;
        private String currency;
        private String description;

    }

    @Getter
    @Setter
    public static class TransactionType {

        private String enumeration;
        private String value;

    }
}
