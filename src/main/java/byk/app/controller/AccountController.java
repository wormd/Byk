package byk.app.controller;

import byk.app.model.Account;
import byk.app.model.exception.TooEarlyException;
import byk.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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

    @GetMapping("/{id}/total")
    public ResponseEntity<?> total(@PathVariable Long id) {
        Optional<Account> opt = accountRepository.findById(id);
        if (opt.isPresent()) {
            Account acc = opt.get();
            try {
                acc.refreshTotal();
                accountRepository.save(acc);
                return ResponseEntity.ok().body(acc);
            } catch (TooEarlyException e) {
                return ResponseEntity.badRequest().body("Refreshing too fast");
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
