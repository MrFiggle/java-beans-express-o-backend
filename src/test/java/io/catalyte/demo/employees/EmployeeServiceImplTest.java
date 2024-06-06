package io.catalyte.demo.employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;
    ArrayList<Employee> employeesInDataBase;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeServiceImpl(employeeRepository);
        employeesInDataBase = new ArrayList<>();
        employeesInDataBase.add((new Employee(true, "John", "Doe", "john@gmail.com")));
        employeesInDataBase.add((new Employee(true, "Jane", "Doe", "jane@gmail.com")));
        employeesInDataBase.add((new Employee(true, "Jonathan", "Dosey", "jonathan@gmail.com")));
    }

    @Test
    public void createEmployee_withValidEmployee_returnsPersistedEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employeesInDataBase.get(1));
        Employee results = employeeService.createEmployee(employeesInDataBase.get(1));
        assertEquals(employeesInDataBase.get(1), results);
    }

    @Test
    public void createEmployee_withInvalidFirstName_throwsBadRequest() {
        Employee employee = employeesInDataBase.get(1);
        employee.setFirstName(null);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            employeeService.createEmployee(employee);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "First name cannot be null. " );

        employee.setFirstName("");
        ResponseStatusException exception2 = assertThrows(ResponseStatusException.class, () -> {
            employeeService.createEmployee(employee);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception2.getStatusCode());
        assertEquals(exception2.getReason(), "First name cannot be empty. " );
    }

    @Test
    public void createEmployee_withInvalidLastName_throwsBadRequest() {
        Employee employee = employeesInDataBase.get(1);
        employee.setLastName(null);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            employeeService.createEmployee(employee);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Last name cannot be null. " );

        employee.setLastName("");
        ResponseStatusException exception2 = assertThrows(ResponseStatusException.class, () -> {
            employeeService.createEmployee(employee);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception2.getStatusCode());
        assertEquals(exception2.getReason(), "Last name cannot be empty. " );
    }

    @Test
    public void createEmployee_withInvalidEmail_throwsBadRequest() {
        Employee employee = employeesInDataBase.get(1);
        employee.setEmail(null);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            employeeService.createEmployee(employee);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Email cannot be null. " );

        employee.setEmail("");
        ResponseStatusException exception2 = assertThrows(ResponseStatusException.class, () -> {
            employeeService.createEmployee(employee);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception2.getStatusCode());
        assertEquals(exception2.getReason(), "Email cannot be empty. " );

        employee.setEmail("john");
        ResponseStatusException exception3 = assertThrows(ResponseStatusException.class, () -> {
            employeeService.createEmployee(employee);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception3.getStatusCode());
        assertEquals(exception3.getReason(), "Email must follow x@x.x format. " );

    }
}
