package byk.app.controller;

import byk.app.model.Supply;
import byk.app.repository.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/supplies")
public class SupplyController {

    @Autowired
    SupplyRepository supplyRepository;

    @GetMapping
    public @ResponseBody
    List<Supply> getList() {
        return supplyRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        Optional<Supply> one = supplyRepository.findById(id);
        if (one.isPresent()) {
            return ResponseEntity.ok(one.get());
        }
        return ResponseEntity.badRequest().body(Map.of("message", "This supply doesn't exist"));
    }

    @PostMapping
    public void create(@RequestBody Supply supply) {
        supplyRepository.save(supply);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Optional<Supply> opt = supplyRepository.findById(id);
        if (opt.isPresent()) {
            supplyRepository.delete(opt.get());
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Supply doesn't exist"));
    }

    @PutMapping
    public void put(@RequestBody Supply supply) {
        supplyRepository.save(supply);
    }
}
