package by.it.restapitestapp.dao;

import by.it.restapitestapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
}
