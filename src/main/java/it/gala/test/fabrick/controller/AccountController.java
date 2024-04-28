package it.gala.test.fabrick.controller;

import it.gala.test.fabrick.service.AccountService;
import it.gala.test.fabrick.service.model.AccountBalanceDTO;
import it.gala.test.fabrick.service.model.AccountTransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.ACCOUNT_PATH)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("balance")
    public ResponseEntity<AccountBalanceDTO> getAccountBalance() {
        return ResponseEntity.ok(accountService.getAccountBalance());
    }

    @GetMapping("transactions")
    public ResponseEntity<List<AccountTransactionDTO>> getAccountTransactionsByDateRange(@RequestParam(name = "from") LocalDateTime from,
                                                                                         @RequestParam(name = "to") LocalDateTime to) {
        return ResponseEntity.ok(accountService.getAccountTransactionsByDateRange(from, to));
    }

}
