package by.it.restapitestapp.service;

import by.it.restapitestapp.dto.EmployeeResponseDto;
import by.it.restapitestapp.entity.Department;
import by.it.restapitestapp.entity.Employee;


public class EmployeeServiceTestDataProvider {

    public static final Long EXISTING_ID = 3L;
    public static final Long NOT_EXISTING_ID = 22L;
    private static final String DEFAULT_FIRST_NAME = "FirstName";
    private static final String DEFAULT_LAST_NAME = "LastName";
    public static final Long DEFAULT_DEPARTMENT_ID = 1L;
    private static final String DEFAULT_DEPARTMENT_TITLE = "Development";
    public static final Employee EXPECTED_EMPLOYEE = generateExpectedEmployee();
    public static final EmployeeResponseDto EXPECTED_EMPLOYEE_DTO = generateExpectedEmployeeDto();

    private static Employee generateExpectedEmployee() {
        return Employee
                .builder()
                .id(EXISTING_ID)
                .firstName(DEFAULT_FIRST_NAME)
                .lastName(DEFAULT_LAST_NAME)
                .department(generateDepartment())
                .build();
    }

    private static EmployeeResponseDto generateExpectedEmployeeDto() {
        return EmployeeResponseDto
                .builder()
                .id(EXISTING_ID)
                .firstName(DEFAULT_FIRST_NAME)
                .lastName(DEFAULT_LAST_NAME)
                .department(DEFAULT_DEPARTMENT_TITLE)
                .build();
    }

    private static Department generateDepartment() {
        return Department
                .builder()
                .id(DEFAULT_DEPARTMENT_ID)
                .title(DEFAULT_DEPARTMENT_TITLE)
                .build();
    }
}
