package it.gala.test.fabrick.client.feign.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountMoneyTransfer {

    private String moneyTransferId;
    private String status;
    private String direction;
    private Creditor creditor;
    private Debtor debtor;
    private String cro;
    private String uri;
    private String trn;
    private String description;
    private LocalDateTime createdDatetime;
    private LocalDateTime accountedDatetime;
    private LocalDate debtorValueDate;
    private LocalDate creditorValueDate;
    private Amount amount;
    private Boolean isUrgent;
    private Boolean isInstant;
    private String feeType;
    private String feeAccountId;
    private List<Fee> fees;
    private Boolean hasTaxRelief;

    @Getter
    @Setter
    public static class Amount {
        private BigDecimal debtorAmount;
        private String debtorCurrency;
        private BigDecimal creditorAmount;
        private String creditorCurrency;
        private LocalDate creditorCurrencyDate;
        private BigDecimal exchangeRate;
    }

    @Getter
    @Setter
    public static class Fee {
        private String feeCode;
        private String description;
        private BigDecimal amount;
        private String currency;
    }

}
