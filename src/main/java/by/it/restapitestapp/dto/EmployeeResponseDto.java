package by.it.restapitestapp.dto;


import by.it.restapitestapp.entity.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {
    private Long id;
    private String firstName;
    private String lastName;

    @Schema(description = "Department name")
    private String department;

    public EmployeeResponseDto(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.department = employee.getDepartment().getTitle();
    }
}
