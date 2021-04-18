package com.awbd.controller;

import java.util.List;

import com.awbd.model.Employee;
import com.awbd.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public String loadEmployeesPageData(ModelAndView model) {
		return findPaginated(1, "firstName", "asc", model);
	}

	@RequestMapping("/employeePage")
	public ModelAndView viewEmployeesPage( ) {

		ModelAndView mv = new ModelAndView("employee_page");
		List<Employee> employeeList = employeeService.getAllEmployees();
		mv.addObject(employeeList);
		loadEmployeesPageData(mv);
		return mv;
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}

	@GetMapping("/employee/info/{id}")
	public String showById(@PathVariable String id, Model model){
		model.addAttribute("employee",
				employeeService.getEmployeeById(Long.valueOf(id)));
		return "update_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		// save employee to database
		employeeService.saveEmployee(employee);
		return "redirect:/employeePage";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get employee from the service
		Employee employee = employeeService.getEmployeeById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/employeePage";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			ModelAndView model) {
		int pageSize = 5;
		
		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Employee> listEmployees = page.getContent();

		model.addObject("currentPage", pageNo);
		model.addObject("totalPages", page.getTotalPages());
		model.addObject("totalItems", page.getTotalElements());
		
		model.addObject("sortField", sortField);
		model.addObject("sortDir", sortDir);
		model.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addObject("listEmployees", listEmployees);

		return "index";
	}





}
