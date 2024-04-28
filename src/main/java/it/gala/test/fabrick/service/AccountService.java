package it.gala.test.fabrick.service;

import it.gala.test.fabrick.exception.ApplicationException;
import it.gala.test.fabrick.service.mapper.AccountMapper;
import it.gala.test.fabrick.service.model.AccountBalanceDTO;
import it.gala.test.fabrick.service.model.AccountTransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountMapper accountMapper;
    private final ApiFabrickConsumerService apiFabrickConsumerService;
    private final AccountTransactionService accountTransactionService;

    public AccountBalanceDTO getAccountBalance() {
        return accountMapper.accountBalanceToDto(apiFabrickConsumerService.getAccountBalance());
    }

    public List<AccountTransactionDTO> getAccountTransactionsByDateRange(LocalDateTime from, LocalDateTime to) {

        if (from == null || to == null || from.isAfter(to)) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "invalid date");
        }

        List<AccountTransactionDTO> transactions = apiFabrickConsumerService.getAccountTransactionsByDateRange(from, to)
                .getList()
                .stream()
                .map(accountMapper::accountTransactionToDto)
                .toList();

        accountTransactionService.persistMany(new ArrayList<>(transactions));
        return transactions;
    }

}
