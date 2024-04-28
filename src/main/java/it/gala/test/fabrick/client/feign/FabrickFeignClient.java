package it.gala.test.fabrick.client.feign;

import it.gala.test.fabrick.client.feign.model.AccountBalance;
import it.gala.test.fabrick.client.feign.model.AccountTransactions;
import it.gala.test.fabrick.client.feign.model.FabrickResponse;
import it.gala.test.fabrick.configuration.feign.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@FeignClient(
        value = "fabrickFeignClient",
        url = "${api.fabrick.base-url}",
        configuration = FeignClientConfiguration.class
)
public interface FabrickFeignClient {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/accounts/{account-id}/balance",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    FabrickResponse<AccountBalance> getAccountBalanceByAccountId(
            @PathVariable("account-id") String accountId,
            @RequestHeader(name = "Auth-Schema") String authSchema,
            @RequestHeader(name = "apiKey") String apiKey
    );

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/accounts/{account-id}/transactions",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    FabrickResponse<AccountTransactions> getAccountTransactionsByAccountIdAndDateRange(
            @PathVariable("account-id") String accountId,
            @RequestParam("fromAccountingDate") LocalDateTime from,
            @RequestParam("toAccountingDate") LocalDateTime to,
            @RequestHeader(name = "Auth-Schema") String authSchema,
            @RequestHeader(name = "apiKey") String apiKey
    );


}
