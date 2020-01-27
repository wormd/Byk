package byk.app.controller;

import byk.app.model.Account;
import byk.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public @ResponseBody Iterable<Account> firstPage() {
        return accountRepository.findAll();
    }

    @PostMapping
    public void create(@RequestParam String name) {
        accountRepository.save(new Account("name"));
    }
}
