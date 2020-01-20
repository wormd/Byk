package byk.app.controller;

import byk.app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import byk.app.EmployeeRepository;



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
    public @ResponseBody Employee delete(@PathVariable("id") Long id) {
         Employee ret = employeeRepository.getOne(id);
         employeeRepository.delete(employeeRepository.getOne(id)); //findOne?
         return ret;
    }

    @PostMapping
    public @ResponseBody Employee create(@RequestBody Employee employee) { //??? puo' ritornare una classe?
         employeeRepository.save(employee);
         return employee;
    }

}
