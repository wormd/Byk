package byk.app.controller;

import byk.app.model.Reminder;
import byk.app.model.Transaction;
import byk.app.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/reminders")
public class ReminderController {

    Page<Transaction> remsPage;

    @Autowired
    private ReminderRepository reminderRepository;

    @GetMapping
    public @ResponseBody ResponseEntity<?> getList() {
        return ResponseEntity.ok(reminderRepository.findAllByDoneIsFalse());
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
