package com.practice.ecommerce.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.ecommerce.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

}
