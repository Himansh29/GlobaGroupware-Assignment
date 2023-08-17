package com.practice.ecommerce.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	private String empName;
	private String phoneNumber;
	private String email;
	private UUID reportsTo;
	private String profileImage;
	
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UUID getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(UUID reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public Employee(String empName, String phoneNumber, String email, UUID reportsTo, String profileImage) {
		super();
		this.empName = empName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.reportsTo = reportsTo;
		this.profileImage = profileImage;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", empName=" + empName + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", reportsTo=" + reportsTo + ", profileImage=" + profileImage + "]";
	}

}
