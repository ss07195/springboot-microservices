package app.web.ss07195.employeeservice.controller;

import app.web.ss07195.employeeservice.model.Employee;
import app.web.ss07195.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final Logger LOGGER=
            LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    EmployeeRepository repository;

    @PostMapping
    public Employee add(@RequestBody Employee employee){
        LOGGER.info("Employee add");
        return repository.add(employee);
    }
    @GetMapping
    public List<Employee> findAll(){
        LOGGER.info("Employee find all");
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Employee findbyId(@PathVariable("id") Long id){
        LOGGER.info("Employee find: id={}", id);
        return repository.findById(id);
    }
    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId){
        LOGGER.info("Employee find: department={}", departmentId);
        return repository.findByDepartment(departmentId);
    }

}
