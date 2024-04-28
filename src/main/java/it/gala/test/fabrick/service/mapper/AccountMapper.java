package it.gala.test.fabrick.service.mapper;

import it.gala.test.fabrick.client.feign.model.AccountBalance;
import it.gala.test.fabrick.client.feign.model.AccountMoneyTransfer;
import it.gala.test.fabrick.client.feign.model.AccountTransactions;
import it.gala.test.fabrick.service.model.AccountBalanceDTO;
import it.gala.test.fabrick.service.model.AccountMoneyTransferDTO;
import it.gala.test.fabrick.service.model.AccountTransactionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountBalanceDTO accountBalanceToDto(AccountBalance source);
    AccountTransactionDTO accountTransactionToDto(AccountTransactions.Transaction source);
    AccountMoneyTransferDTO accountMoneyTransferToDto(AccountMoneyTransfer source);
}
