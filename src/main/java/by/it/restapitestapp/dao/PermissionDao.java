package by.it.restapitestapp.dao;

import by.it.restapitestapp.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDao extends JpaRepository<Permission, Long> {
}
