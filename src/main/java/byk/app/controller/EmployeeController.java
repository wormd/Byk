package byk.app.controller;

import byk.app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import byk.app.repository.EmployeeRepository;

import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employees")
public class EmployeeController {


    @Autowired
    private EmployeeRepository employeeRepository;

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
         employeeRepository.save(employee);
         return employee;
    }

    @PutMapping("/{id}")
    public @ResponseBody Employee patch(@RequestBody Employee employee) {
        Optional<Employee> target = employeeRepository.findById(employee.getId());
        return employee;
    }

}
