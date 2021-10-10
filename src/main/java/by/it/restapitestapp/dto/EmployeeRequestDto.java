package by.it.restapitestapp.dto;

import by.it.restapitestapp.entity.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class EmployeeRequestDto {
    private String firstName;
    private String lastName;

    @Schema(description = "Department ID")
    private Long departmentId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    public Employee getEmployeeFromDto() {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        return employee;
    }
}
