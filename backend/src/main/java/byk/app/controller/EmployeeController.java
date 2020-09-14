package byk.app.controller;

import byk.app.model.Account;
import byk.app.model.Employee;
import byk.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import byk.app.repository.EmployeeRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employees")
public class EmployeeController {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping//(produces = "application/json")
     public @ResponseBody Iterable<Employee> firstPage() {
        return employeeRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        employeeRepository.deleteById(id);
    }

    @PostMapping
    public @ResponseBody Employee create(@RequestBody Employee employee) {
        Account acc = new Account();
        acc.setName(employee.getName()+" "+employee.getSurname());
        acc.setDescr("Created for "+employee.getName()+" "+employee.getSurname()+ ", employed since "+employee.getSince());
        employee.setAccount(acc);
        accountRepository.save(acc);
        employeeRepository.save(employee);
        return employee;
    }

    @PutMapping
    public @ResponseBody Employee update(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
        // Optional<Employee> target = employeeRepository.findById(employee.getId());

    }

}
