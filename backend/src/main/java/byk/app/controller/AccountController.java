package byk.app.controller;

import byk.app.model.Account;
import byk.app.model.Transaction;
import byk.app.model.exception.TooEarlyException;
import byk.app.repository.AccountRepository;
import byk.app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

     //accounts/?id=0,1,2
    @GetMapping
    public @ResponseBody Iterable<Account> findFilter(@RequestParam(name = "id", required=false) List<Long> ids) {
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
                return ResponseEntity.badRequest().body(Map.of("message","Refreshing too fast"));
            }
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Account doesn't exist"));
    }

    @GetMapping("/{account_id}/transactions")
    public @ResponseBody Iterable<Transaction> trans(@PathVariable("account_id") Long account_id,
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                     @RequestParam(value = "after") LocalDateTime after,
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                     @RequestParam(value = "before") LocalDateTime before) {
        return transactionRepository.findByAccountAndDates(account_id, after.toLocalDate(), before.toLocalDate());
    }
}
