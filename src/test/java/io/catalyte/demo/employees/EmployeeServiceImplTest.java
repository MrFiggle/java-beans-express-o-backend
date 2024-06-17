package io.catalyte.demo.employees;

import io.catalyte.demo.vendors.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    EmployeeService employeeService;

    Employee employee;

    Employee updatedEmployeeTest1;
    Employee updatedEmployeeTest2;
    Employee updatedEmployeeTest3;
    Employee updatedEmployeeTest4;

    @Mock
    EmployeeRepository employeeRepository;
    ArrayList<Employee> employeesInDataBase;

    @BeforeEach
    public void setUp() {

        // Mock repository
        employeeService = new EmployeeServiceImpl(employeeRepository);
        employeesInDataBase = new ArrayList<>();
        employeesInDataBase.add((new Employee(true, "John", "Doe", "john@gmail.com")));
        employeesInDataBase.add((new Employee(true, "Jane", "Doe", "jane@gmail.com")));
        employeesInDataBase.add((new Employee(true, "Jonathan", "Dosey", "jonathan@gmail.com")));

        // Test entities for update method
        updatedEmployeeTest1 = new Employee(true, "Jane", "Marianne", "jmarianne@gmail.com");
        updatedEmployeeTest2 = new Employee(true, null, "Marianne", "jmarianne@gmail.com");
        updatedEmployeeTest3 = new Employee(true, "Jane", null, "jmarianne@gmail.com");
        updatedEmployeeTest4 = new Employee(true, "Jane", "Marianne", null);

        // Mock repository behavior
        employee = employeesInDataBase.get(1);
        lenient().when(employeeRepository.getReferenceById(1)).thenReturn(employee);
        lenient().when(employeeRepository.existsById(1)).thenReturn(true);
    }

    @Test
    public void createEmployee_withValidEmployee_returnsPersistedEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employeesInDataBase.get(1));
        Employee results = employeeService.createEmployee(employeesInDataBase.get(1));
        assertEquals(employeesInDataBase.get(1), results);
    }

    @Test
    public void createEmployee_withInvalidFirstName_throwsBadRequest() {
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

    @Test
    public void getAllEmployees_noArg_returnsListOfEmployees(){
        List<Vendor> toReturn = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employeesInDataBase);
        List<Employee> list = employeeService.getAllEmployees();
        assertEquals(3, list.size());
    }

    @Test
    public void getAllEmployees_noArgs_throwsResponseStatusException(){
        when(employeeRepository.findAll()).thenThrow(new ResponseStatusException(HttpStatusCode.valueOf(500)));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            employeeService.getAllEmployees();
        });
        assertEquals("500 INTERNAL_SERVER_ERROR \"There was an internal error while fetching your data\"", exception.getMessage() );
    }

    @Test
    public void updateEmployee_withValidEmployee_returnsPersistedEmployee() {
        boolean expectedResult = false;
        lenient().when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployeeTest1);
        try {
            Employee updatedEmployee = employeeService.updateEmployee(updatedEmployeeTest1, 1);
            expectedResult = updatedEmployee.equals(updatedEmployeeTest1);
        } catch (ResponseStatusException e) {
            fail("Exception occurred during update: " + e.getMessage());
        }
        assertTrue(expectedResult);
    }

    @Test
    public void updateEmployee_withInvalidFirstName_throwsBadRequest() {

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            employeeService.updateEmployee(updatedEmployeeTest2, 1);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "First name cannot be null. " );

        updatedEmployeeTest2.setFirstName("");
        ResponseStatusException exception2 = assertThrows(ResponseStatusException.class, () -> {
            employeeService.updateEmployee(updatedEmployeeTest2, 1);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception2.getStatusCode());
        assertEquals(exception2.getReason(), "First name cannot be empty. " );
    }
//
    @Test
    public void updateEmployee_withInvalidLastName_throwsBadRequest() {

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            employeeService.updateEmployee(updatedEmployeeTest3, 1);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Last name cannot be null. " );

        updatedEmployeeTest3.setLastName("");
        ResponseStatusException exception2 = assertThrows(ResponseStatusException.class, () -> {
            employeeService.updateEmployee(updatedEmployeeTest3, 1);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception2.getStatusCode());
        assertEquals(exception2.getReason(), "Last name cannot be empty. " );
    }

    @Test
    public void updateEmployee_withInvalidEmail_throwsBadRequest() {

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            employeeService.updateEmployee(updatedEmployeeTest4, 1);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Email cannot be null. " );

        updatedEmployeeTest4.setEmail("");
        ResponseStatusException exception2 = assertThrows(ResponseStatusException.class, () -> {
            employeeService.updateEmployee(updatedEmployeeTest4, 1);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception2.getStatusCode());
        assertEquals(exception2.getReason(), "Email cannot be empty. " );

        updatedEmployeeTest4.setEmail("john");
        ResponseStatusException exception3 = assertThrows(ResponseStatusException.class, () -> {
            employeeService.updateEmployee(updatedEmployeeTest4, 1);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception3.getStatusCode());
        assertEquals(exception3.getReason(), "Email must follow x@x.x format. " );

    }
}