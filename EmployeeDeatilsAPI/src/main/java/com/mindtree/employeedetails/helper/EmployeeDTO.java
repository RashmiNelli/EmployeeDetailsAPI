package com.mindtree.employeedetails.helper;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mindtree.employeedetails.beans.Employee;

/**
 * @author nelli
 */
@XmlRootElement(name="employees")
public class EmployeeDTO {
	
	private List<Employee> employeeList;
	
	/**
	 * To fetch all the employee details
	 * @return employeeList
	 */
	@XmlElement(name = "employee")
	@JsonProperty("employees")
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	
	/**
	 * @param employeeList
	 */
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
}
