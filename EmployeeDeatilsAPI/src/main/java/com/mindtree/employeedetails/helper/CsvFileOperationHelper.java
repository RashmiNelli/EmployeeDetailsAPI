package com.mindtree.employeedetails.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.mindtree.employeedetails.beans.Employee;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * Class to perform CSV related operations
 * @author nelli
 */
public class CsvFileOperationHelper {
	
	//Setting the path of CSV file
	private static final String CSV_FILE_PATH = "src/main/resources/employeedetails.csv";
	
	/**
	 * Read & Fetch list of all employees from CSV file
	 * @return
	 */
	public List<Employee> readFromCsvFile() {

		BufferedReader fileReader = null;
		CsvToBean<Employee> csvToBean = null;
		List<Employee> employees = null;

		try {
			fileReader = new BufferedReader(new FileReader(CSV_FILE_PATH));
			csvToBean = new CsvToBeanBuilder<Employee>(fileReader).withType(Employee.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			employees = csvToBean.parse();

			for (Employee employee : employees) {
				System.out.println(employee);
			}

			return employees;
		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Closing fileReader/csvParser Error!");
				e.printStackTrace();
			}
		}
		return employees;
	}
	
	/**
	 * For inserting a record into CSV file
	 * @param rows
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 */
	public void insertToCsvFile(List<Employee> rows)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		try (Writer writer = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH));) {
			StatefulBeanToCsv<Employee> beanToCsv = new StatefulBeanToCsvBuilder<Employee>(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
			beanToCsv.write(rows);
		}

	}
}
