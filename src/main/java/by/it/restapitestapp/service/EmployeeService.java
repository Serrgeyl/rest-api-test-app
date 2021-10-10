package by.it.restapitestapp.service;

import by.it.restapitestapp.dao.DepartmentDao;
import by.it.restapitestapp.dao.EmployeeDao;
import by.it.restapitestapp.dao.PermissionDao;
import by.it.restapitestapp.dto.EmployeeRequestDto;
import by.it.restapitestapp.dto.EmployeeResponseDto;
import by.it.restapitestapp.dto.PermissionDto;
import by.it.restapitestapp.dto.ResidenceDto;
import by.it.restapitestapp.entity.Department;
import by.it.restapitestapp.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDao employeeDao;
    private final DepartmentDao departmentDao;
    private final PermissionDao permissionDao;

    public List<EmployeeResponseDto> findAll(Integer page, Integer size) {
        String DEFAULT_SORT_COLUMN = "id";
        Pageable paging = PageRequest.of(page, size, Sort.by(DEFAULT_SORT_COLUMN).ascending());
        Page<Employee> employeeList = employeeDao.findAll(paging);
        return employeeList.getContent().stream().map(EmployeeResponseDto::new).collect(Collectors.toList());
    }

    public EmployeeResponseDto findById(Long id) {
        Employee employee = employeeDao.findById(id).get();
        return new EmployeeResponseDto(employee);
    }

    public EmployeeResponseDto addNew(EmployeeRequestDto employeeRequestDto) {
        Long DEFAULT_PERMISSION_ID = 1L;
        Long departmentId = employeeRequestDto.getDepartmentId();
        Department department = departmentDao.findById(departmentId).get();
        Employee employee = employeeRequestDto.getEmployeeFromDto();
        employee.setDepartment(department);
        employee.setPermissions(List.of(permissionDao.findById(DEFAULT_PERMISSION_ID).get()));
        Employee savedEmployee = employeeDao.save(employee);
        return new EmployeeResponseDto(savedEmployee);
    }

    public EmployeeResponseDto update(Long id, EmployeeRequestDto employeeRequestDto) {
        Employee employeeFromDb = employeeDao.findById(id).get();
        Department department = departmentDao.findById(employeeRequestDto.getDepartmentId()).get();
        employeeFromDb.setFirstName(employeeRequestDto.getFirstName());
        employeeFromDb.setLastName(employeeRequestDto.getLastName());
        employeeFromDb.setDepartment(department);
        Employee updatedEmployee = employeeDao.save(employeeFromDb);
        return new EmployeeResponseDto(updatedEmployee);
    }

    public List<PermissionDto> retrieveEmployeePermissions(Long id) {
        Employee employee = employeeDao.findById(id).get();
        return employee.getPermissions()
                .stream()
                .map(PermissionDto::new)
                .collect(Collectors.toList());
    }

    public ResidenceDto retrieveEmployeeResidence(Long id) {
        Employee employee = employeeDao.findById(id).get();
        return new ResidenceDto(employee.getResidence());
    }

    public void deleteById(Long id) {
        employeeDao.deleteById(id);
    }
}
