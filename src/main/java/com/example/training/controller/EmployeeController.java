package com.example.training.controller;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.training.mapper.impl.EmployeeServiceImpl;
import com.example.training.model.Employees;


@Controller
public class EmployeeController {

	public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeServiceImpl employeeservice;
	

	@RequestMapping(value = { "/", "/index" })
	public String getIndex() {
		return "index";
	}
	
	@RequestMapping(value = "/employees", method = RequestMethod.POST)
	public String createPost(Model model, @Valid @ModelAttribute("employee") Employees emp) {
		logger.info("Creating employees >> " + emp);
		this.employeeservice.save(emp);
		model.addAttribute("employee", emp);
		return "redirect:/employees";
	}
	
	/*
	 * Delete 
	 */
	@RequestMapping(value = "/employees/{id}/delete", method = RequestMethod.DELETE)
	public String deletePost(@PathVariable("id") Long id) {
		logger.info("Deleting employees id:" + id);
		this.employeeservice.delete(id);;
		return "redirect:/employees";
	}
	
	/*
	 * Update 
	 */
	@RequestMapping(value = "/employees/update", method = RequestMethod.PUT)
	public String updatePost(@Valid @ModelAttribute("employee") Employees emp) {
		logger.info("Updating employees >> " + emp);
		this.employeeservice.update(emp);
		return "form";
	}
	
	/*
	 * List all employee
	 */
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public String listAllPosts(Model model) {
		logger.info("Litsing all employees...");
		
		model.addAttribute("employees", employeeservice.findAll());
		return "employee-index";
	}
	
	/*
	 * Display employee details
	 */
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
	public String displayPostDetails(Model model, @PathVariable("id") Long id) {
		logger.info("Displaying employees details >> id: " + id);
		model.addAttribute("employees", employeeservice.find(id));
		return "employee-index";
	}
	

//	@RequestMapping("/employee/search")
//	public String search(@RequestParam("s") String s, Model model) {
//		if (s.equals("")) {
//			return "redirect:/employee";
//		}
//
//		model.addAttribute("employees", employeeservice.search(s));
//		return "list";
//	}
	}
