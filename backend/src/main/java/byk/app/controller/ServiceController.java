package byk.app.controller;

import byk.app.model.Service;
import byk.app.model.Task;
import byk.app.repository.ServiceRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping
    public @ResponseBody
    List<Service> getList() {
        return serviceRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        Optional<Service> one = serviceRepository.findById(id);
        if (one.isPresent()) {
            return ResponseEntity.ok(one.get());
        }
        return ResponseEntity.badRequest().body(Map.of("message", "This service doesn't exist"));
    }

    @GetMapping("/{id}/toggle")
    public @ResponseBody
    ResponseEntity<?> toggle(@PathVariable("id") Long id) {
        Optional<Service> one = serviceRepository.findById(id);
        if (one.isPresent()) {
            Service ser = one.get();
            ser.toggleActive();
            serviceRepository.save(ser);
            return ResponseEntity.ok(ser);
        }
        return ResponseEntity.badRequest().body(Map.of("message", "This service doesn't exist"));
    }

    @PostMapping
    public void create(@RequestBody Service service) {
        serviceRepository.save(service);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Optional<Service> opt = serviceRepository.findById(id);
        if (opt.isPresent()) {
            serviceRepository.delete(opt.get());
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Service doesn't exist"));
    }

    @PutMapping
    public void put(@RequestBody Service service) {
//        TODO: implement & update just service columns
//        serviceRepository.save(service);
    }
}
