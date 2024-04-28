package it.gala.test.fabrick.service.mapper;

import it.gala.test.fabrick.client.feign.model.AccountTransactions;
import it.gala.test.fabrick.persistence.sql.entity.AccountTransaction;
import it.gala.test.fabrick.persistence.sql.entity.AccountTransactionType;
import it.gala.test.fabrick.service.model.AccountTransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AccountTransactionMapper {

    @Mapping(target = "type", source = "type", qualifiedByName = "toEntityAccountTransactionType")
    AccountTransaction toEntity(AccountTransactionDTO source);

    @Named("toEntityAccountTransactionType")
    AccountTransactionType toEntityAccountTransactionType(AccountTransactions.TransactionType source);
}
