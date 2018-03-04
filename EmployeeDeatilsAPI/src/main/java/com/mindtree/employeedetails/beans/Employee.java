package com.mindtree.employeedetails.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;

/**
 * Employee Bean Class
 * JavaBean domain object representing Employee
 * and it implements Serializable
 * 
 * @author nelli
 * @version 1.0
 */
@XmlRootElement
public class Employee implements Serializable {
	
	/**
	 *  Instance variables
	 */
	private static final long serialVersionUID = 1L;
	
	@CsvBindByName(column = "employee-id")
	@JsonProperty("employee_id")
	private long empId;

	@CsvBindByName(column = "employee-name")
	@JsonProperty("employee_name")
	private String empName;

	@CsvBindByName(column = "employee-designation")
	@JsonProperty("employee_designation")
	private String empDesignation;
	
	/**
	 * @return the empId
	 */
	@XmlElement(name = "employee_id")
	public long getEmpId() {
		return empId;
	}
	
	/**
	 * @param empId 
	 * 			the empId to set
	 */
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	
	/**
	 * @return the empName
	 */
	@XmlElement(name = "employee_name")
	public String getEmpName() {
		return empName;
	}
	
	/**
	 * @param empName 
	 * 			the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	/**
	 * @return the empDesignation
	 */
	@XmlElement(name = "employee_designation")
	public String getEmpDesignation() {
		return empDesignation;
	}
	
	/**
	 * @param empDesignation 
	 * 			the empDesignation to set
	 */
	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (empId ^ (empId >>> 32));
		return result;
	}
	
	/* 
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (empId != other.empId)
			return false;
		return true;
	}
	
	/* 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empDesignation=" + empDesignation + "]";
	}

}
