package by.it.restapitestapp.service;

import by.it.restapitestapp.dao.EmployeeDao;
import by.it.restapitestapp.dto.EmployeeResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.NoSuchElementException;
import java.util.Optional;

import static by.it.restapitestapp.service.EmployeeServiceTestDataProvider.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeDao mockedEmployeeDao;

    @Test
    public void whenFindByValidId_thenEmployeeShouldBeFound() {
        when(mockedEmployeeDao.findById(EXISTING_ID)).thenReturn(Optional.ofNullable(EXPECTED_EMPLOYEE));
        EmployeeResponseDto found = employeeService.findById(EXISTING_ID);
        assertEquals(EXPECTED_EMPLOYEE_DTO, found);
    }

    @Test
    public void whenFindByInvalidId_thenExceptionShouldBeThrown() {
        when(mockedEmployeeDao.findById(NOT_EXISTING_ID)).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class, () -> employeeService.findById(NOT_EXISTING_ID));
    }
}
