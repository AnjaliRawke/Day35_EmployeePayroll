package com.bridgelabz;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

public class EmployeePayrollTest {
	@Test
	public void givenThreeEmployee_WhenWrittenToFile_ShouldMatchEmployeeEntries(){
		EmployeePayrollData[] arrayOfEmps = {
				new EmployeePayrollData(1,"Anjali",25000,),
				new EmployeePayrollData(2,"ravi",26000),
				new EmployeePayrollData(3,"apeksha",28000)
		};
		EmployeePayrollService employeePayrollService;
		employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmps));
		employeePayrollService.writeEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);
		employeePayrollService.printEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);
		long entries = employeePayrollService.countEntries(EmployeePayrollService.IOService.FILE_IO);
		Assertions.assertEquals(3, entries);
	}

	@Test
	public void givenFile_OnReadingFromFile_ShouldMatchCount(){
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		List<EmployeePayrollData> entries = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);
		Assertions.assertEquals(0, entries.size());
	}

	@Test
	public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount(){
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		List<EmployeePayrollData> employeePayrollDataList = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
		Assertions.assertEquals(5, employeePayrollDataList.size());
	}

	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldMatch() throws EmployeePayrollException {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		List<EmployeePayrollData> employeePayrollDataList = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
		employeePayrollService.updateEmployeeSalary("Mayur",1000000.0);
		boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Mayur");
		Assertions.assertTrue(result);
	}
}
