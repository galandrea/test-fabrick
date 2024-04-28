package it.gala.test.fabrick.client.feign.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Creditor {

    @NotBlank
    private String name;
    @NotNull
    private Account account;
    private Address address;

    @Getter
    @Setter
    public static class Address {
        private String address;
        private String city;
        private String countryCode;
    }
}
