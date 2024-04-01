package com.ted.milanopizza.controller;

import com.ted.milanopizza.model.Employee;
import com.ted.milanopizza.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:5173")

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employeeList = new ArrayList<>();
            employeeRepository.findAll().forEach(employeeList::add);

            if (employeeList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employeeList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employeeData = employeeRepository.findById(id);

        if (employeeData.isPresent()) {

            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {

        Employee employeeObj = employeeRepository.save(employee);

        return new ResponseEntity<>(employeeObj, HttpStatus.OK);
    }
    @PostMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @RequestBody Employee newEmployeeData) {
        Optional<Employee> oldEmployeeData = employeeRepository.findById(id);

        if (oldEmployeeData.isPresent()) {
            Employee updatedEmployeeData = oldEmployeeData.get();

            // Check and update fields if they are present in the JSON request
            if (newEmployeeData.getFirstName() != null) {
                updatedEmployeeData.setFirstName(newEmployeeData.getFirstName());
            }
            if (newEmployeeData.getLastName() != null) {
                updatedEmployeeData.setLastName(newEmployeeData.getLastName());
            }
            if (newEmployeeData.getStatus() != null) {
                updatedEmployeeData.setStatus(newEmployeeData.getStatus());
            }

            // new entity here

            Employee employeeObj = employeeRepository.save(updatedEmployeeData);
            return new ResponseEntity<>(employeeObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
