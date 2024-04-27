package it.gala.test.fabrick.service;

import it.gala.test.fabrick.client.feign.FabrickFeignClient;
import it.gala.test.fabrick.client.feign.model.AccountBalance;
import it.gala.test.fabrick.client.feign.model.FabrickResponse;
import it.gala.test.fabrick.configuration.ApiFabrickConfiguration;
import it.gala.test.fabrick.exception.ApplicationException;
import it.gala.test.fabrick.service.mapper.AccountMapper;
import it.gala.test.fabrick.service.model.AccountBalanceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiFabrickConsumerService {

    private final ApiFabrickConfiguration apiFabrickConfiguration;
    private final FabrickFeignClient fabrickFeignClient;

    public AccountBalance getAccountBalance() {
        ResponseEntity<FabrickResponse<AccountBalance>> response = fabrickFeignClient.getAccountBalanceByAccountId(
                apiFabrickConfiguration.getAccountId(),
                apiFabrickConfiguration.getAuthSchema(),
                apiFabrickConfiguration.getApiKey()
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return Objects.requireNonNull(response.getBody()).getPayload();
        }
        throw new ApplicationException(
                HttpStatus.valueOf(response.getStatusCode().value()),
                Optional.ofNullable(response.getBody())
                        .map(body -> body.getErrors().stream()
                                .map(e -> "[code:" + e.getCode() + " description:" + e.getDescription() + " params:" + e.getParams() + "]")
                                .collect(Collectors.joining("")))
                        .orElse("unreachable error")
                );
    }

}
