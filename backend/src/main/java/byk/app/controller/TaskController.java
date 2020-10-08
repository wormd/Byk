package byk.app.controller;

import byk.app.model.Task;
import byk.app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public @ResponseBody ResponseEntity<?> getList(@RequestParam(required = false) Long afters,
                                                   @RequestParam(required = false) Long befores,
                                                   @RequestParam(required = false) Boolean done) {
        int page = 0;
        int size = 30;
        Page<Task> remPage;
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime after = null, before = null;

        if (afters != null) after = date.plus(afters, ChronoUnit.SECONDS);
        if (befores != null) before = date.plus(befores, ChronoUnit.SECONDS);
        if (done == null) done = false;

        remPage = taskRepository.findAllByParams(after, before, done,
                PageRequest.of(page, size, Sort.by("dueBy").ascending()));
        return ResponseEntity.ok(remPage.getContent());
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Task find(@PathVariable Long id) {
        return taskRepository.getOne(id);
    }

    @PostMapping
    public void create(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Optional<Task> opt = taskRepository.findById(id);
        if (opt.isPresent()) {
            taskRepository.delete(opt.get());
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Reminder doesn't exist"));
    }

    @PutMapping
    public void put(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @GetMapping("/{id}/done")
    public @ResponseBody
    Task done(@PathVariable Long id) {
        Task rem = taskRepository.getOne(id);
        if (rem.getCycle()) {
            LocalDateTime nextDueDate = rem.getDueBy().plusSeconds(rem.getCycletime());
            Task nextRem = new Task(rem);
            nextRem.setDueBy(nextDueDate);
            taskRepository.save(nextRem);
        }
        rem.done();
        taskRepository.save(rem);
        return rem;
    }

    @GetMapping("/{id}/undone")
    public @ResponseBody
    Task undone(@PathVariable Long id) {
        Task rem = taskRepository.getOne(id);
        rem.undone();
        taskRepository.save(rem);
        return rem;
    }

}
