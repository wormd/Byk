package byk.app.controller;

import byk.app.model.Cashbookline;
import byk.app.repository.CashbooklineRepository;
import byk.app.model.AccountCashbookJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cashbook")
public class CashbooklineController {

    @Autowired
    private CashbooklineRepository cashbooklineRepository;

    @GetMapping
    public Iterable<Cashbookline> firstPage() {
        LocalDate today = LocalDate.now();
        return cashbooklineRepository.findByDateAfterAndDateBefore(today.with(firstDayOfYear()),
                today.with(lastDayOfYear()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        // TODO: cancella tutte le entry di AccountCashbookJoin che ha cashbook_id questo id e poi cancella come sotto
        cashbooklineRepository.deleteById(id);
    }

    @PostMapping
    public @ResponseBody Cashbookline create(@RequestBody Cashbookline line) {
//        Set<AccountCashbookJoin> list = line.getAccountCashbookJoins();
//        for (AccountCashbookJoin joi: list) {
//            joi.
//        }
        cashbooklineRepository.save(line);
        return line;
    }

}
