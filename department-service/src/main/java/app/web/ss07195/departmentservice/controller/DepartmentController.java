package app.web.ss07195.departmentservice.controller;

import app.web.ss07195.departmentservice.client.EmployeeClient;
import app.web.ss07195.departmentservice.model.Department;
import app.web.ss07195.departmentservice.model.Employee;
import app.web.ss07195.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private static final Logger LOGGER=
            LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private DepartmentRepository repository;
    @PostMapping
    public Department add(@RequestBody Department department){
        LOGGER.info("Department add");
        return repository.addDepartment(department);
    }
    @GetMapping
    public List<Department> findAll(){
        LOGGER.info("Department find all");

        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Department findbyId(@PathVariable Long id){
        LOGGER.info("Department find: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        LOGGER.info("Department find");
        List<Department> departments
                = repository.findAll();
        departments.forEach(department ->
                department.setEmployees(
                        employeeClient.findByDepartment(department.getId())));
        return  departments;
    }

}
