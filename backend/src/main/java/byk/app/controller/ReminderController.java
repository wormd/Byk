package byk.app.controller;

import byk.app.model.Reminder;
import byk.app.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/reminders")
public class ReminderController {

    @Autowired
    private ReminderRepository reminderRepository;

    @GetMapping
    public @ResponseBody ResponseEntity<?> getList(@RequestParam(required = false) Long afters,
                                                   @RequestParam(required = false) Long befores,
                                                   @RequestParam(required = false) Boolean done) {
        int page = 0;
        int size = 30;
        Page<Reminder> remPage;
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime after = null, before = null;

        if (afters != null) after = date.plus(afters, ChronoUnit.SECONDS);
        if (befores != null) before = date.plus(befores, ChronoUnit.SECONDS);
        if (done == null) done = false;

        remPage = reminderRepository.findAllByParams(after, before, done, PageRequest.of(page, size, Sort.by("dueBy").ascending()));
        return ResponseEntity.ok(remPage.getContent());
    }

    @GetMapping("/{id}")
    public @ResponseBody Reminder find(@PathVariable Long id) {
        return reminderRepository.getOne(id);
    }

    @PostMapping
    public void create(@RequestBody Reminder reminder) {
        reminderRepository.save(reminder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Optional<Reminder> opt = reminderRepository.findById(id);
        if (opt.isPresent()) {
            reminderRepository.delete(opt.get());
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Reminder doesn't exist"));
    }

    @PutMapping
    public void put(@RequestBody Reminder reminder) {
        reminderRepository.save(reminder);
    }
}
