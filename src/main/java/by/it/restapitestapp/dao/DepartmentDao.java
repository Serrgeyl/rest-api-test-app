package by.it.restapitestapp.dao;

import by.it.restapitestapp.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDao extends JpaRepository<Department, Long> {
}
