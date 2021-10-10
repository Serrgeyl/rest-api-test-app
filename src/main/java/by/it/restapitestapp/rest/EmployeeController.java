package by.it.restapitestapp.rest;


import by.it.restapitestapp.dto.EmployeeRequestDto;
import by.it.restapitestapp.dto.EmployeeResponseDto;
import by.it.restapitestapp.dto.PermissionDto;
import by.it.restapitestapp.dto.ResidenceDto;
import by.it.restapitestapp.entity.Employee;
import by.it.restapitestapp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequiredArgsConstructor
@Tag(name = "Employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Get all employees")
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponseDto>> findAll(
            @RequestParam(defaultValue = "0") @Parameter(description = "Page number. Default value = 0") Integer page,
            @RequestParam(defaultValue = "10") @Parameter(description = "Number of records per page. Default value = 10") Integer size) {
        List<EmployeeResponseDto> employeeList = employeeService.findAll(page, size);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @Operation(summary = "Add new employee")
    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponseDto> addNew(@RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto newEmployee = employeeService.addNew(employeeRequestDto);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @Operation(summary = "Get employee")
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponseDto> getById(@PathVariable @Parameter(description = "Employee's ID") Long id) {
        EmployeeResponseDto employeeResponseDto = employeeService.findById(id);
        return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
    }

    @Operation(summary = "Get employee's permissions")
    @GetMapping("/employees/{id}/permissions")
    public ResponseEntity<List<PermissionDto>> retrieveEmployeePermissions(@PathVariable @Parameter(description = "Employee's ID") Long id) {
        List<PermissionDto> permissions = employeeService.retrieveEmployeePermissions(id);
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @Operation(summary = "Get employee's residence")
    @GetMapping("/employees/{id}/residence")
    public ResponseEntity<ResidenceDto> retrieveEmployeeResidence(@PathVariable @Parameter(description = "Employee's ID") Long id) {
        ResidenceDto residence = employeeService.retrieveEmployeeResidence(id);
        return new ResponseEntity<>(residence, HttpStatus.OK);
    }

    @Operation(summary = "Edit employee")
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponseDto> update(@PathVariable @Parameter(description = "Employee's ID") Long id,
                                                      @RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto updatedEmployee = employeeService.update(id, employeeRequestDto);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete employee")
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> delete(@PathVariable @Parameter(description = "Employee's ID") Long id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchElementExceptionHandle() {
        String message = String.format("%s %s", new Timestamp(new Date().getTime()), "Bad request");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
