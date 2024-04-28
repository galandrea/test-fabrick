package it.gala.testfabrick.service;

import it.gala.test.fabrick.client.feign.model.Account;
import it.gala.test.fabrick.client.feign.model.AccountBalance;
import it.gala.test.fabrick.client.feign.model.AccountMoneyTransfer;
import it.gala.test.fabrick.client.feign.model.AccountMoneyTransfersRequest;
import it.gala.test.fabrick.client.feign.model.AccountTransactions;
import it.gala.test.fabrick.client.feign.model.Creditor;
import it.gala.test.fabrick.service.AccountService;
import it.gala.test.fabrick.service.AccountTransactionService;
import it.gala.test.fabrick.service.ApiFabrickConsumerService;
import it.gala.test.fabrick.service.mapper.AccountMapper;
import it.gala.test.fabrick.service.model.AccountBalanceDTO;
import it.gala.test.fabrick.service.model.AccountMoneyTransferDTO;
import it.gala.test.fabrick.service.model.AccountTransactionDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private ApiFabrickConsumerService apiFabrickConsumerService;

    @Mock
    private AccountTransactionService accountTransactionService;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void AccountService_GetAccountBalance_ReturnAccountBalanceDTO() {
        AccountBalanceDTO mockAccountBalanceDTO = new AccountBalanceDTO();
        mockAccountBalanceDTO.setDate(LocalDate.parse("2024-04-28"));
        mockAccountBalanceDTO.setBalance(BigDecimal.valueOf(100));
        mockAccountBalanceDTO.setAvailableBalance(BigDecimal.valueOf(100));
        mockAccountBalanceDTO.setCurrency("EUR");

        AccountBalance mockAccountBalance = new AccountBalance();
        mockAccountBalance.setDate(LocalDate.parse("2024-04-28"));
        mockAccountBalance.setBalance(BigDecimal.valueOf(100));
        mockAccountBalance.setAvailableBalance(BigDecimal.valueOf(100));
        mockAccountBalance.setCurrency("EUR");

        when(apiFabrickConsumerService.getAccountBalance()).thenReturn(mockAccountBalance);
        when(accountMapper.accountBalanceToDto(any())).thenReturn(mockAccountBalanceDTO);

        AccountBalanceDTO result = accountService.getAccountBalance();

        assertEquals(mockAccountBalance.getDate(), result.getDate());
        assertEquals(mockAccountBalance.getBalance(), result.getBalance());
        assertEquals(mockAccountBalance.getAvailableBalance(), result.getAvailableBalance());
        assertEquals(mockAccountBalance.getCurrency(), result.getCurrency());
    }

    @Test
    public void AccountService_GetAccountTransactionsByDateRange_ReturnAccountTransactionDTO() {
        LocalDateTime from = LocalDateTime.parse("2024-04-01T00:00:00");
        LocalDateTime to = LocalDateTime.parse("2024-04-01T00:00:00");

        AccountTransactions.TransactionType transactionType = new AccountTransactions.TransactionType();
        transactionType.setEnumeration("ENUMERATION");
        transactionType.setValue("VALUE");

        AccountTransactionDTO mockAccountTransactionDTO = new AccountTransactionDTO();
        mockAccountTransactionDTO.setTransactionId("123456789");
        mockAccountTransactionDTO.setOperationId("123456789");
        mockAccountTransactionDTO.setAccountingDate(LocalDate.parse("2024-04-07"));
        mockAccountTransactionDTO.setType(transactionType);
        mockAccountTransactionDTO.setAmount(BigDecimal.valueOf(-100));
        mockAccountTransactionDTO.setCurrency("EUR");
        mockAccountTransactionDTO.setDescription("Description");
        List<AccountTransactionDTO> mockTransactionDTOList = List.of(mockAccountTransactionDTO);

        AccountTransactions mockAccountTransactions = new AccountTransactions();
        AccountTransactions.Transaction mockTransaction = new AccountTransactions.Transaction();
        mockTransaction.setTransactionId("123456789");
        mockTransaction.setOperationId("123456789");
        mockTransaction.setAccountingDate(LocalDate.parse("2024-04-07"));
        mockTransaction.setType(transactionType);
        mockTransaction.setAmount(BigDecimal.valueOf(-100));
        mockTransaction.setCurrency("EUR");
        mockTransaction.setDescription("Description");
        mockAccountTransactions.setList(Collections.singletonList(mockTransaction));

        when(apiFabrickConsumerService.getAccountTransactionsByDateRange(from, to)).thenReturn(mockAccountTransactions);
        when(accountMapper.accountTransactionToDto(any())).thenReturn(mockAccountTransactionDTO);
        Mockito.doNothing().when(accountTransactionService).persistMany(any());

        List<AccountTransactionDTO> result = accountService.getAccountTransactionsByDateRange(from, to);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(mockTransactionDTOList.size(), result.size());
        assertEquals(mockTransactionDTOList.getFirst().getTransactionId(), result.getFirst().getTransactionId());
        assertEquals(mockTransactionDTOList.getFirst().getOperationId(), result.getFirst().getOperationId());
        assertEquals(mockTransactionDTOList.getFirst().getAccountingDate(), result.getFirst().getAccountingDate());
        assertEquals(mockTransactionDTOList.getFirst().getValueDate(), result.getFirst().getValueDate());
        assertEquals(mockTransactionDTOList.getFirst().getType().getEnumeration(), result.getFirst().getType().getEnumeration());
        assertEquals(mockTransactionDTOList.getFirst().getType().getValue(), result.getFirst().getType().getValue());
        assertEquals(mockTransactionDTOList.getFirst().getAmount(), result.getFirst().getAmount());
        assertEquals(mockTransactionDTOList.getFirst().getCurrency(), result.getFirst().getCurrency());
        assertEquals(mockTransactionDTOList.getFirst().getDescription(), result.getFirst().getDescription());
    }

    @Test
    public void AccountService_CreateMoneyTransfer_ReturnAccountMoneyTransferDTO() {
        AccountMoneyTransfersRequest request = new AccountMoneyTransfersRequest();

        AccountMoneyTransfer mockAccountMoneyTransfer = new AccountMoneyTransfer();
        mockAccountMoneyTransfer.setMoneyTransferId("123456789");
        Creditor creditor = new Creditor();
        creditor.setName("Mario Rossi");
        Account account = new Account();
        account.setAccountCode("IT60X0542811101000000123456");
        creditor.setAccount(account);
        mockAccountMoneyTransfer.setCreditor(creditor);
        mockAccountMoneyTransfer.setCreatedDatetime(LocalDateTime.now());
        AccountMoneyTransfer.Amount amount = new AccountMoneyTransfer.Amount();
        amount.setCreditorAmount(BigDecimal.valueOf(100));
        amount.setCreditorCurrency("EUR");
        amount.setCreditorCurrencyDate(LocalDate.parse("2024-04-07"));
        mockAccountMoneyTransfer.setAmount(amount);
        mockAccountMoneyTransfer.setDescription("Description");

        when(apiFabrickConsumerService.createMoneyTransfer(request)).thenReturn(mockAccountMoneyTransfer);

        AccountMoneyTransferDTO accountMoneyTransferDTO = new AccountMoneyTransferDTO();
        accountMoneyTransferDTO.setMoneyTransferId("123456789");
        accountMoneyTransferDTO.setCreditor(creditor);
        accountMoneyTransferDTO.setCreatedDatetime(LocalDateTime.now());
        accountMoneyTransferDTO.setAmount(amount);
        accountMoneyTransferDTO.setDescription("Description");

        when(accountMapper.accountMoneyTransferToDto(mockAccountMoneyTransfer)).thenReturn(accountMoneyTransferDTO);

        AccountMoneyTransferDTO result = accountService.createMoneyTransfer(request);

        assertNotNull(result);
        assertEquals(accountMoneyTransferDTO.getMoneyTransferId(), result.getMoneyTransferId());
        assertEquals(accountMoneyTransferDTO.getCreditor().getName(), result.getCreditor().getName());
        assertEquals(accountMoneyTransferDTO.getCreditor().getAccount().getAccountCode(), result.getCreditor().getAccount().getAccountCode());
        assertEquals(accountMoneyTransferDTO.getCreatedDatetime(), result.getCreatedDatetime());
        assertEquals(accountMoneyTransferDTO.getAmount().getCreditorAmount(), result.getAmount().getCreditorAmount());
        assertEquals(accountMoneyTransferDTO.getAmount().getCreditorCurrency(), result.getAmount().getCreditorCurrency());
        assertEquals(accountMoneyTransferDTO.getAmount().getCreditorCurrencyDate(), result.getAmount().getCreditorCurrencyDate());
        assertEquals(accountMoneyTransferDTO.getDescription(), result.getDescription());
    }

}