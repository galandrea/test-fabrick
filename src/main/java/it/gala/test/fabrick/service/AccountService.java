package it.gala.test.fabrick.service;

import it.gala.test.fabrick.service.mapper.AccountMapper;
import it.gala.test.fabrick.service.model.AccountBalanceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountMapper accountMapper;
    private final ApiFabrickConsumerService apiFabrickConsumerService;

    public AccountBalanceDTO getAccountBalance() {
        return accountMapper.accountBalanceToDto(apiFabrickConsumerService.getAccountBalance());
    }
}
