package com.practice.ecommerce.entity.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.ecommerce.entity.Employee;
import com.practice.ecommerce.repo.EmployeeRepository;

@RestController
public class EmployeeController {
	
	private final EmployeeRepository employeeRepository;
	
	@Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
	
	@PostMapping("/employees")
	public ResponseEntity<UUID> addEmployee(@RequestBody Employee employee){
		Employee savedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(savedEmployee.getId());
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
	    List<Employee> employees = employeeRepository.findAll();
	    return ResponseEntity.ok(employees);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
	    employeeRepository.deleteById(id);
	    return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Void> updateEmployeeById(@PathVariable UUID id, @RequestBody Employee updatedEmployee) {
	    Optional<Employee> existingEmployee = employeeRepository.findById(id);
	    if (existingEmployee.isPresent()) {
	        Employee employeeToUpdate = existingEmployee.get();
	        employeeToUpdate.setEmpName(updatedEmployee.getEmpName());
	        employeeToUpdate.setPhoneNumber(updatedEmployee.getPhoneNumber());
	        employeeToUpdate.setEmail(updatedEmployee.getEmail());
	        employeeToUpdate.setReportsTo(updatedEmployee.getReportsTo());
	        employeeToUpdate.setProfileImage(updatedEmployee.getProfileImage());
	        employeeRepository.save(employeeToUpdate);
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}


	
	@GetMapping("/employees/{id}/manager/{level}")
	public ResponseEntity<Employee> getNthLevelManager(@PathVariable UUID id, @PathVariable int level) {
	    Optional<Employee> employeeOptional = employeeRepository.findById(id);
	    
	    if (employeeOptional.isPresent()) {
	        Employee employee = employeeOptional.get();
	        UUID managerId = employee.getReportsTo();
	        
	        while (managerId != null && level > 0) {
	            Optional<Employee> managerOptional = employeeRepository.findById(managerId);
	            if (managerOptional.isPresent()) {
	                employee = managerOptional.get();
	                managerId = employee.getReportsTo();
	                level--;
	            } else {
	                break;
	            }
	        }
	        
	        return ResponseEntity.ok(employee);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
//	
//	
//	@GetMapping("/employees")
//	public ResponseEntity<Page<Employee>> getEmployeesWithPaginationAndSorting(
//	    @RequestParam(defaultValue = "0") int page,
//	    @RequestParam(defaultValue = "10") int size,
//	    @RequestParam(defaultValue = "employeeName") String sortBy
//	) {
//	    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//	    Page<Employee> employeesPage = employeeRepository.findAll(pageable);
//	    return ResponseEntity.ok(employeesPage);
//	}



}
