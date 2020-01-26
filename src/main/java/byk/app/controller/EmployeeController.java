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
    public void delete(@PathVariable("id") Long id) {
        employeeRepository.deleteById(id);
    }

//        Employee ret = employeeRepository.findAll()
//                .stream()
//                .filter(e->e.getId().equals(id))
//                .findFirst().orElse(null);
//        try {
//            employeeRepository.delete(ret);
//        } catch (IllegalArgumentException e) {
//            System.out.println("Couldn't delete Employee");
//        }
//        return ret;

//        for (Employee employee: employeeRepository.findAll()) {
//            if (employee.getId().equals(id)) {
//                employeeRepository.delete(employee);
//                return employee;
//            }
//        }
//        return null;

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
