package byk.app.controller;

import byk.app.model.Account;
import byk.app.model.Transaction;
import byk.app.repository.AccountRepository;
import byk.app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @GetMapping("/transactions/{id}")
    public Iterable<Transaction> firstPage(@PathVariable("id") @RequestParam Long id) {
        LocalDate today = LocalDate.now();
        Account originAccount = accountRepository.getOne(id);
        return transactionRepository.findByOriginAndDateAfterAndDateBefore(originAccount,
                today.with(firstDayOfYear()),
                today.with(lastDayOfYear()));
    }

    @DeleteMapping("/transactions/{id}")
    public void delete(@PathVariable("id") Long id) {
        transactionRepository.deleteById(id);
    }

    @PostMapping("/accounts/{id}/transactions")
    public @ResponseBody
    Transaction create(@PathVariable("id") Long account_id, @RequestBody Transaction transaction) {
        Account account = accountRepository.getOne(account_id);
        transaction.setOrigin(account);
        Set<Transaction> trans = account.getTransactions();
        trans.add(transaction);
        account.setTransactions(trans);
        return transactionRepository.save(transaction);
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
