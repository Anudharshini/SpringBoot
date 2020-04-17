package com.practice.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.springboot.thymeleafdemo.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	//add method to sort by first name
	public List<Employee> findAllByOrderByFirstName();
}
