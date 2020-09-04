package byk.app.controller;

import byk.app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import byk.app.repository.EmployeeRepository;


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
    public @ResponseBody Employee replace(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return employeeRepository.save(employee);
        // Optional<Employee> target = employeeRepository.findById(employee.getId());

    }

}