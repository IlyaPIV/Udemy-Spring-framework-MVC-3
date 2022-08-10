package com.udemy.spring.rest.controller;

import com.udemy.spring.rest.entity.Employee;
import com.udemy.spring.rest.exception_handling.NoSuchEmployeeException;
import com.udemy.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees(){
        List<Employee> allEmployees = employeeService.getAllEmployees();

        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id){
        Employee employee = employeeService.getEmployee(id);

        if (employee==null) {
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in DataBase");
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;
    }

//    @PutMapping("/employees")
    @RequestMapping(value = "/employees",
                    produces = "application/json",
                    method = RequestMethod.PUT)
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;
    }

//    @DeleteMapping("/employees/{id}")
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable int id){

        Employee employee = employeeService.getEmployee(id);
        if (employee==null) throw new NoSuchEmployeeException("No Employee with such ID in DataBase");

        employeeService.deleteEmployee(id);

        return "Employee with id = " + id + " was deleted.";
    }
}
