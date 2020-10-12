package byk.app.controller;

import byk.app.model.Service;
import byk.app.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ClientController {

    @Autowired
    ServiceRepository clientRepository;

    @GetMapping
    public @ResponseBody
    List<Service> getList() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        Optional<Service> one = clientRepository.findById(id);
        if (one.isPresent()) {
            return ResponseEntity.ok(one.get());
        }
        return ResponseEntity.badRequest().body(Map.of("message", "This client doesn't exist"));
    }

    @PostMapping
    public void create(@RequestBody Service service) {
        clientRepository.save(service);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Optional<Service> opt = clientRepository.findById(id);
        if (opt.isPresent()) {
            clientRepository.delete(opt.get());
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Service doesn't exist"));
    }

    @PutMapping
    public void put(@RequestBody Service service) {
        clientRepository.save(service);
    }
}
