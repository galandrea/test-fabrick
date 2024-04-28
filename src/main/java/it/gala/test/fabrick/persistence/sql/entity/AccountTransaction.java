package it.gala.test.fabrick.persistence.sql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "acctrnsct_opid_index", columnList = "operationId"),
        @Index(name = "acctrnsct_currency_index", columnList = "currency")
})
public class AccountTransaction implements Serializable {

    @Id
    private String transactionId;

    private String operationId;
    private LocalDate accountingDate;
    private LocalDate valueDate;

    @ManyToOne
    @JoinColumn(name = "ID_TRANSACTION_TYPE", referencedColumnName = "ID_TRANSACTION_TYPE")
    private AccountTransactionType type;

    private BigDecimal amount;
    private String currency;
    private String description;

}
