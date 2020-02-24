package byk.app.controller;

import byk.app.model.Account;
import byk.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public @ResponseBody Iterable<Account> find(@RequestParam(required=false) List<Long> ids) {
        if (ids != null) {
            System.out.println("Id list:"+ids);

            return accountRepository.findAllById(ids);
        }
        System.out.println("Id list:"+ids);

        return accountRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Account one(@PathVariable Long id) {
        return accountRepository.getOne(id);
    }

    @PostMapping
    public void create(@RequestParam String name, String address) {
        accountRepository.save(new Account(name,address));
    }

//    @GetMapping("/all")
//    public @ResponseBody Iterable<Account> all() {
//        return accountRepository.findAll();
//    }
}
