package com.example.amCrudH2.repo;

import com.example.amCrudH2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
