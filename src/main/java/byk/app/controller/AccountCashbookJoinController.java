package byk.app.controller;

import byk.app.model.AccountCashbookJoin;
import byk.app.repository.AccountCashbookJoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("accountcashbookjoin")
public class AccountCashbookJoinController {

    @Autowired
    AccountCashbookJoinRepository acRepository;

    @GetMapping("/{id}")
    public @ResponseBody AccountCashbookJoin one(@PathVariable Long id) {
        return acRepository.getOne(id);
    }

}
