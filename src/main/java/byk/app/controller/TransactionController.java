package byk.app.controller;

import byk.app.model.Account;
import byk.app.model.Transaction;
import byk.app.repository.AccountRepository;
import byk.app.repository.TransactionRepository;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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

    Page<Transaction> transactions;

    @DeleteMapping("/transactions/{trans_id}")
    public ResponseEntity<?> delete(@PathVariable("trans_id") Long trans_id) {
        Optional<Transaction> opt = transactionRepository.findById(trans_id);
        if (opt.isPresent()) {
            Transaction trans = opt.get();
            Account target = trans.getTarget();
            Account origin = trans.getOrigin();

            origin.removeOriginTransaction(trans);
            target.removeTargetTransaction(trans);

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
        origin.addOriginTransaction(trans);

        Account target = trans.getTarget();
        target.addTotal(trans.getAmount());
        target.addTargetTransaction(trans);
        return transactionRepository.save(trans);
    }

    @GetMapping("/transactions/count")
    public @ResponseBody ResponseEntity<?> count() {
        HashMap<String, Integer> res = new HashMap();
        res.put("count", transactions.getSize());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/transactions")
    public @ResponseBody ResponseEntity<?> get(@RequestParam String by,
                                               @RequestParam int page,
                                               @RequestParam int size,
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                   @RequestParam(value = "after", required = false)
                                                           LocalDateTime after,
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                   @RequestParam(value = "before", required = false)
                                                           LocalDateTime before) {
        if (by.equals("date")) {
            this.transactions = transactionRepository.findByDateAndDates(after, before,
                    PageRequest.of(page, size, Sort.by("date").descending()));
        } else if (by.equals("created")) {
            this.transactions = transactionRepository.findByCreatedAndDates(after, before,
                    PageRequest.of(page, size, Sort.by("created").descending()));
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(this.transactions.getContent());
    }
}
