package byk.app.controller;

import byk.app.model.Client;
import byk.app.model.Service;
import byk.app.model.Supply;
import byk.app.repository.ClientRepository;
import byk.app.repository.ServiceRepository;
import byk.app.repository.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
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

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    SupplyRepository supplyRepository;

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

    // ADD/REMOVE METHODS

    @GetMapping("/{id}/clients/{client_id}/add")
    public @ResponseBody
    ResponseEntity<?> addClient(@PathVariable("id") Long id, @PathVariable("client_id") Long client_id) {
        Optional<Service> serviceOpt = serviceRepository.findById(id);
        if (serviceOpt.isPresent()) {
            Optional<Client> clientOpt = clientRepository.findById(client_id);
            if (clientOpt.isPresent()) {
                Service service = serviceOpt.get();
                service.addClient(clientOpt.get());
                serviceRepository.save(service);
                return ResponseEntity.ok(serviceOpt.get());
            } else {
                ResponseEntity.badRequest().body(Map.of("message", "This client doesn't exist"));
            }
        }
        return ResponseEntity.badRequest().body(Map.of("message", "This service doesn't exist"));
    }

    @GetMapping("/{id}/clients/{client_id}/remove")
    public @ResponseBody
    ResponseEntity<?> removeClient(@PathVariable("id") Long id, @PathVariable("client_id") Long client_id) {
        Optional<Service> serviceOpt = serviceRepository.findById(id);
        if (serviceOpt.isPresent()) {
            Optional<Client> clientOpt = clientRepository.findById(client_id);
            if (clientOpt.isPresent()) {
                Service service = serviceOpt.get();
                Client client = clientOpt.get();
                List<Client> clients = service.getClients();
                if (clients.contains(client)) {
                    service.removeClient(client);
                    serviceRepository.save(service);
                    return ResponseEntity.ok(serviceOpt.get());
                } else {
                    ResponseEntity.badRequest().body(Map.of("message", "This client isn't registered on this service"));
                }
            } else {
                ResponseEntity.badRequest().body(Map.of("message", "This client doesn't exist"));
            }
        }
        return ResponseEntity.badRequest().body(Map.of("message", "This service doesn't exist"));
    }

    // TODO: FIXTHIS Not adding correctly?
    @GetMapping("/{id}/supplies/{supply_id}/add")
    public @ResponseBody
    ResponseEntity<?> addSupply(@PathVariable("id") Long id, @PathVariable("supply_id") Long supplyId) {
        Optional<Service> serviceOpt = serviceRepository.findById(id);
        if (serviceOpt.isPresent()) {
            Optional<Supply> supplyOpt = supplyRepository.findById(supplyId);
            if (supplyOpt.isPresent()) {
                Service service = serviceOpt.get();
                service.addSupply(supplyOpt.get());
                serviceRepository.save(service);
                return ResponseEntity.ok(serviceOpt.get());
            } else {
                ResponseEntity.badRequest().body(Map.of("message", "This supply doesn't exist"));
            }
        }
        return ResponseEntity.badRequest().body(Map.of("message", "This service doesn't exist"));
    }

    @GetMapping("/{id}/supplies/{supply_id}/remove")
    public @ResponseBody
    ResponseEntity<?> removeSupply(@PathVariable("id") Long id, @PathVariable("supply_id") Long supplyId) {
        Optional<Service> serviceOpt = serviceRepository.findById(id);
        if (serviceOpt.isPresent()) {
            Optional<Supply> supplyOpt = supplyRepository.findById(supplyId);
            if (supplyOpt.isPresent()) {
                Service service = serviceOpt.get();
                Supply supply = supplyOpt.get();
                List<Supply> supplies = service.getSupplies();
                if (supplies.contains(supply)) {
                    service.removeSupply(supply);
                    serviceRepository.save(service);
                    return ResponseEntity.ok(serviceOpt.get());
                } else {
                    ResponseEntity.badRequest().body(Map.of("message", "This supply isn't registered on this service"));
                }
            } else {
                ResponseEntity.badRequest().body(Map.of("message", "This supply doesn't exist"));
            }
        }
        return ResponseEntity.badRequest().body(Map.of("message", "This service doesn't exist"));
    }
}
