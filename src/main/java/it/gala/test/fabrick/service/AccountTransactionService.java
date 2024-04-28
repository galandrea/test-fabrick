package it.gala.test.fabrick.service;

import it.gala.test.fabrick.client.feign.model.AccountTransactions;
import it.gala.test.fabrick.persistence.sql.entity.AccountTransaction;
import it.gala.test.fabrick.persistence.sql.repository.AccountTransactionRepository;
import it.gala.test.fabrick.persistence.sql.repository.AccountTransactionTypeRepository;
import it.gala.test.fabrick.service.mapper.AccountTransactionMapper;
import it.gala.test.fabrick.service.model.AccountTransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountTransactionRepository accountTransactionRepository;
    private final AccountTransactionTypeRepository accountTransactionTypeRepository;
    private final AccountTransactionMapper accountTransactionMapper;

    @Async
    public void persistMany(List<AccountTransactionDTO> toPersistList) {

        toPersistList.removeIf(dto ->
                !this.accountTransactionIsWellFormed(dto) ||
                accountTransactionRepository.existsByTransactionId(dto.getTransactionId())
        );

        accountTransactionRepository.saveAll(
                toPersistList.stream()
                        .map(accountTransactionMapper::toEntity)
                        .map(this::handleAccountTransactionType)
                        .toList()
        );
    }

    private AccountTransaction handleAccountTransactionType(AccountTransaction e) {

        e.setType(
                accountTransactionTypeRepository.findByEnumerationAndValue(e.getType().getEnumeration(), e.getType().getValue())
                        .orElseGet(() -> accountTransactionTypeRepository.save(e.getType()))
        );

        return e;
    }

    private boolean accountTransactionIsWellFormed(AccountTransactionDTO toCheck) {
        return Arrays.stream(AccountTransactionDTO.class.getDeclaredFields())
                .allMatch(f -> {
                    try {
                        f.setAccessible(true);
                        if (f.getType().isAssignableFrom(AccountTransactions.TransactionType.class)) {
                            AccountTransactions.TransactionType type = (AccountTransactions.TransactionType) f.get(toCheck);
                            return type != null && (type.getEnumeration() != null || type.getValue() != null);
                        }
                        return f.get(toCheck) != null;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
