package it.gala.test.fabrick.client.feign.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class AccountMoneyTransfersRequest {

    @NotNull
    private Creditor creditor;
    private LocalDate executionDate;
    private String uri;
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal amount;
    @NotBlank
    private String currency;
    private Boolean isUrgent;
    private Boolean isInstant;
    private String feeType;
    private String feeAccountId;

    @Getter
    @Setter
    public static class TaxRelief {
        private String taxReliefId;
        @NotNull
        private Boolean isCondoUpgrade;
        @NotBlank
        private String creditorFiscalCode;
        @NotBlank
        private String beneficiaryType;
        private NaturalPersonBeneficiary naturalPersonBeneficiary;
        private LegalPersonBeneficiary legalPersonBeneficiary;
    }

    @Getter
    @Setter
    public static class NaturalPersonBeneficiary {
        @NotBlank
        private String fiscalCode1;
        private String fiscalCode2;
        private String fiscalCode3;
        private String fiscalCode4;
        private String fiscalCode5;
    }

    @Getter
    @Setter
    public static class LegalPersonBeneficiary {
        @NotBlank
        private String fiscalCode;
        private String legalRepresentativeFiscalCode;
    }
}
