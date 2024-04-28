package it.gala.test.fabrick.service;

import it.gala.test.fabrick.client.feign.FabrickFeignClient;
import it.gala.test.fabrick.client.feign.model.AccountBalance;
import it.gala.test.fabrick.client.feign.model.AccountTransactions;
import it.gala.test.fabrick.client.feign.model.FabrickResponse;
import it.gala.test.fabrick.configuration.ApiFabrickConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApiFabrickConsumerService {

    private final ApiFabrickConfiguration apiFabrickConfiguration;
    private final FabrickFeignClient fabrickFeignClient;

    public AccountBalance getAccountBalance() {
        FabrickResponse<AccountBalance> response = fabrickFeignClient.getAccountBalanceByAccountId(
                apiFabrickConfiguration.getAccountId(),
                apiFabrickConfiguration.getAuthSchema(),
                apiFabrickConfiguration.getApiKey()
        );

        return response.getPayload();
    }

    public AccountTransactions getAccountTransactionsByDateRange(LocalDateTime from, LocalDateTime to) {

        FabrickResponse<AccountTransactions> response = fabrickFeignClient.getAccountTransactionsByAccountIdAndDateRange(
                apiFabrickConfiguration.getAccountId(),
                from,
                to,
                apiFabrickConfiguration.getAuthSchema(),
                apiFabrickConfiguration.getApiKey()
        );

        return response.getPayload();
    }

}
