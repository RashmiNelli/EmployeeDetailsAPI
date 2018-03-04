package com.mindtree.employeedetails.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.employeedetails.beans.Employee;
import com.mindtree.employeedetails.helper.EmployeeDTO;
import com.mindtree.employeedetails.service.EmployeeService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * Controller used to showcase all the operations that
 * can be performed on Employee class
 * 
 * @author nelli
 *
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	/** 
	 * @return all the employee details
	 * http://localhost:8080/employee 
	 */
	@GetMapping()
	public EmployeeDTO getEmployeesList() {
		EmployeeDTO response = new EmployeeDTO();
		 response.setEmployeeList(empService.getEmployeesList());
		 return response;
	}
	
	/**
	 * @param employeeName
	 * @return a particular employee by Name
	 * http://localhost:8080/employee/findBy?employee_name=Ramya
	 */
	@GetMapping("/findBy")
	public EmployeeDTO findEmployeeByName(@RequestParam(value = "employee_name") String employeeName) {
		EmployeeDTO response = new EmployeeDTO();
		 response.setEmployeeList(empService.findEmployeeByName(employeeName));
		 return response;
	}
	
	/**
	 * @param employeeId
	 * @return a particular employee by Id
	 * http://localhost:8080/employee/1703
	 */
	@GetMapping("/{employee_id}")
	public Employee findEmployeeById(@PathVariable(value = "employee_id") long employeeId) {
		 return  empService.findEmployeeById(employeeId);
	}
	
	/**
	 * @param employeeDTO
	 * @return 
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	@PostMapping()
	public String createEmployees(@RequestBody EmployeeDTO employeeDTO )
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException,IllegalArgumentException {
		
		return empService.createEmployees(employeeDTO.getEmployeeList());
	}
	
	/**
	 * @param employee
	 * @return
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 */
	@PutMapping()
	public String updateEmployee(@RequestBody Employee employee)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {

		return empService.updateEmployee(employee);

	}
	
	/**
	 * @param employee
	 * @return
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 */
	@DeleteMapping()
	public String deleteEmployee(@RequestBody Employee employee) 
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {

		return empService.deleteEmployee(employee);

	}

}
