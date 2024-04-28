package it.gala.test.fabrick.service.model;

import it.gala.test.fabrick.client.feign.model.Creditor;
import it.gala.test.fabrick.client.feign.model.Debtor;
import it.gala.test.fabrick.client.feign.model.AccountMoneyTransfer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountMoneyTransferDTO {

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
    private AccountMoneyTransfer.Amount amount;
    private Boolean isUrgent;
    private Boolean isInstant;
    private String feeType;
    private String feeAccountId;
    private List<AccountMoneyTransfer.Fee> fees;
    private Boolean hasTaxRelief;

}
