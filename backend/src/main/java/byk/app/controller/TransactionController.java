package byk.app.controller;

import byk.app.model.Account;
import byk.app.model.Transaction;
import byk.app.repository.AccountRepository;
import byk.app.repository.TransactionRepository;
import org.apache.coyote.Response;
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
import java.util.Map;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    Page<Transaction> transactionsPage;

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
        return ResponseEntity.badRequest().body(Map.of("message", "Transaction doesn't exist"));
    }

    @PostMapping("/transactions")
    public @ResponseBody Transaction create(@RequestBody Transaction trans) {
        Float amount = trans.getAmount();
        Account origin = trans.getOrigin();
        origin.addTotal(-amount);
        origin.addOriginTransaction(trans);

        Account target = trans.getTarget();
        target.addTotal(amount);
        target.addTargetTransaction(trans);
        return transactionRepository.save(trans);
    }

    @GetMapping("/transactions/count")
    public @ResponseBody ResponseEntity<?> count() {
        if (this.transactionsPage != null) {
            HashMap<String, Long> res = new HashMap<String, Long>();
            res.put("count", this.transactionsPage.getTotalElements());
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().body(Map.of("message", "No transactions retrieved yet."));
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
        page -= 1;
        if (by.equals("date")) {
            this.transactionsPage = transactionRepository.findByDateAndDates(after, before,
                    PageRequest.of(page, size, Sort.by("date").descending()));
        } else if (by.equals("created")) {
            this.transactionsPage = transactionRepository.findByCreatedAndDates(after, before,
                    PageRequest.of(page, size, Sort.by("created").descending()));
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "Parameter 'by' should be 'date' or 'created'"));
        }
        return ResponseEntity.ok(this.transactionsPage.getContent());
    }

    @GetMapping("/transactions-m")
    public @ResponseBody ResponseEntity<?> findFilter(@RequestParam(name = "ids") List<Long> ids,
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                        @RequestParam(value = "after") LocalDateTime after,
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                          @RequestParam(value = "before") LocalDateTime before) {
        System.out.println(ids);
        if (ids == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Provide 'id' list"));
        }
        return ResponseEntity.ok(transactionRepository.findByAccountListAndDate(ids, after.toLocalDate(), before.toLocalDate()));
    }
}
