package com.mindtree.employeedetails.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mindtree.employeedetails.beans.Employee;
import com.mindtree.employeedetails.helper.CsvFileOperationHelper;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * EmployeeService class to perform 
 * all the logical operations
 * @author nelli
 */
@Service
public class EmployeeService {
	
	private CsvFileOperationHelper csv;
	
	/**
	 * Creating a new employee record
	 * @param employeeList
	 * @return
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	public String createEmployees(List<Employee> employeeList)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, IllegalArgumentException {
		csv = new CsvFileOperationHelper();
		List<Employee> existingEmpList = csv.readFromCsvFile();

		try {
			if (!(employeeList.isEmpty() || employeeList.size() == 0)) {
				if (!(existingEmpList.isEmpty() || existingEmpList.size() == 0)) {
					Set<Employee> employeeSet = new HashSet<Employee>(existingEmpList);
					employeeList.forEach(e -> {

						if (!employeeSet.add(e)) {
							throw new IllegalArgumentException(
									e.getEmpId() + " this employee already exist in the DB...!!!");
						}

					});

					employeeList.addAll(existingEmpList);
				}

			}

			csv.insertToCsvFile(employeeList);
			return "Successfully inserted the employees";
		} catch (IllegalArgumentException ex) {
			return ex.getMessage();
		}

	}
	
	/**
	 * Get list of all employees
	 * @return
	 */
	public List<Employee> getEmployeesList() {
		csv = new CsvFileOperationHelper();
		return csv.readFromCsvFile();
	}
	
	/**
	 * Get employee by Name
	 * @param employeeName
	 * @return
	 */
	public List<Employee> findEmployeeByName(String employeeName) {
		System.out.println("emp name " + employeeName);
		List<Employee> employeeList = csv.readFromCsvFile();
		List<Employee> employeeReturnList = new ArrayList<>();

		if (!(employeeList.isEmpty() && employeeList.size() == 0)) {

			employeeReturnList = employeeList.stream().filter(e -> e.getEmpName().equals(employeeName))
					.collect(Collectors.toList());
		}
		return employeeReturnList;
	}
	
	/**
	 * Get employee by ID
	 * @param employeeId
	 * @return
	 */
	public Employee findEmployeeById(long employeeId) {
		Employee employee = new Employee();
		List<Employee> employeeList = csv.readFromCsvFile();
		if (!(employeeList.isEmpty() && employeeList.size() == 0)) {
			employee = employeeList.stream().filter(e -> e.getEmpId() == employeeId).findFirst()
					.orElseThrow(() -> new IllegalArgumentException("This Employee does not exist in Db "));
		}
		return employee;
	}
	
	/**
	 * Updating existing employee record
	 * @param employee
	 * @return
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 */
	public String updateEmployee(Employee employee)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		csv = new CsvFileOperationHelper();

		String updateMsg = "Sorry issue while updating the employee, Employee Does not Exist in DB  !!!";

		List<Employee> employeeList = csv.readFromCsvFile();

		if (!(employeeList.isEmpty() || employeeList.size() == 0)) {

			try {
				int index = employeeList.indexOf(employee);

				if (employeeList.set(index, employee) != null) {
					csv.insertToCsvFile(employeeList);
					updateMsg = "Successfully updated the employee";
				}
			} catch (ArrayIndexOutOfBoundsException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return updateMsg;

	}
	
	/**
	 * Deleting existing employee record
	 * @param employee
	 * @return
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 */
	public String deleteEmployee(@RequestBody Employee employee)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {

		csv = new CsvFileOperationHelper();
		String deleteMsg = "Sorry issue while deleting the employee, Employee Does not Exist in DB !!!";

		List<Employee> employeeList = csv.readFromCsvFile();

		if (!(employeeList.isEmpty() || employeeList.size() == 0)) {

			try {
				int index = employeeList.indexOf(employee);

				if (employeeList.remove(index) != null) {
					csv.insertToCsvFile(employeeList);
					deleteMsg = "Successfully deleted the employee";
				}
			} catch (ArrayIndexOutOfBoundsException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return deleteMsg;

	}

}
