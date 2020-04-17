package com.practice.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practice.springboot.thymeleafdemo.entity.Employee;
import com.practice.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	
	
	@Autowired
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}


	//expose /employees and return list
	@GetMapping("/list")
	public String listEmployees(Model theModel){
		
		//get list from db
		List<Employee> theEmployee=employeeService.findAll();
		
		//add to spring model
		theModel.addAttribute("employees",theEmployee);
		
		return "employeeHtml/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create model attribute to bind form data
		
		Employee theEmployee=new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employeeHtml/employee-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel) {
		
		//get employee from service
		Employee theEmployee=employeeService.findById(theId);
		
		//set employee id as model attribute to prepopulate in form data
		theModel.addAttribute("employee", theEmployee);
		
		//send to form
		return "employeeHtml/employee-form";
	}

	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		employeeService.save(theEmployee);
		
		//use a redirect to avoid duplicate submission
		return "redirect:/employees/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		
		//delete employee
		employeeService.deleteById(theId);
		
		//redirect
		return "redirect:/employees/list";
	}
	
	
}









