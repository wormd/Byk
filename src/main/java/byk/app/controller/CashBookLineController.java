package byk.app.controller;

import byk.app.model.CashBookLine;
import byk.app.repository.CashBookLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@RestController
@RequestMapping("/cashbook")
public class CashBookLineController {

    @Autowired
    private CashBookLineRepository cashBookLineRepository;

    @GetMapping
    public Iterable<CashBookLine> firstPage() {
        LocalDate today = LocalDate.now();
        return cashBookLineRepository.findByDateAfterAndDateBefore(today.with(firstDayOfYear()),
                today.with(lastDayOfYear()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        cashBookLineRepository.deleteById(id);
    }

    @PostMapping
    public @ResponseBody
    CashBookLine create(@RequestBody CashBookLine line) {
        cashBookLineRepository.save(line);
        return line;
    }

}
