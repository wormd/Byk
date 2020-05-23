package byk.app.controller;

import byk.app.model.Account;
import byk.app.model.Transaction;
import byk.app.repository.AccountRepository;
import byk.app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @DeleteMapping("/account/{acc_id}/transactions/{trans_id}")
    public ResponseEntity<?> delete(@PathVariable("acc_id") Long acc_id, @PathVariable("trans_id") Long trans_id) {
        Optional<Transaction> opt = transactionRepository.findById(trans_id);
        if (opt.isPresent()) {
            Transaction trans = opt.get();
            Account target = trans.getTarget();
            Account origin = trans.getOrigin();

            origin.removeTransUpdateTotal(trans);

            accountRepository.save(origin);
            accountRepository.save(target);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/transactions")
    public @ResponseBody Transaction create(@RequestBody Transaction trans) {
        Account origin = trans.getOrigin();
        origin.addTotal(-trans.getAmount());
        origin.addTransaction(trans);
        Account target = trans.getTarget();
        target.addTotal(trans.getAmount());
        target.addTransaction(trans);
        return transactionRepository.save(trans);
    }

    @GetMapping("/accounts/{account_id}/transactions")
    public @ResponseBody Iterable<Transaction> trans(@PathVariable("account_id") Long account_id,
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                     @RequestParam("after") LocalDate after,
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                     @RequestParam("before") LocalDate before)
    {
        return transactionRepository.findByAccountAndDates(account_id, after, before);
    }

}
