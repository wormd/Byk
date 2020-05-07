package byk.app.controller;

import byk.app.model.Account;
import byk.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

     //accounts/?id=0,1,2
    @GetMapping
    public @ResponseBody Iterable<Account> findFilter(@PathVariable("id") @RequestParam(required=false) List<Long> ids) {
        if (ids == null) {
            return accountRepository.findAll();
        }
        return accountRepository.findAllById(ids);
    }

    @GetMapping("/{account_id}")
    public @ResponseBody Account find(@PathVariable Long account_id) {
        return accountRepository.getOne(account_id);
    }

    @PostMapping
    public void create(@RequestBody Account account) {
        accountRepository.save(account);
    }

}
