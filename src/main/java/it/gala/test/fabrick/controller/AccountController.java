package it.gala.test.fabrick.controller;

import it.gala.test.fabrick.service.AccountService;
import it.gala.test.fabrick.service.model.AccountBalanceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ACCOUNT_PATH)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("balance")
    public ResponseEntity<AccountBalanceDTO> getAccountBalance() {
        return ResponseEntity.ok(accountService.getAccountBalance());
    }

}
