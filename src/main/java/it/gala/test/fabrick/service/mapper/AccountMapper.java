package it.gala.test.fabrick.service.mapper;

import it.gala.test.fabrick.client.feign.model.AccountBalance;
import it.gala.test.fabrick.service.model.AccountBalanceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountBalanceDTO accountBalanceToDto(AccountBalance source);
}
