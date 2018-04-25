package pdir1736MV;

import pdir1736MV.controller.EmployeeController;
import pdir1736MV.enumeration.DidacticFunction;
import pdir1736MV.model.Employee;
import pdir1736MV.repository.interfaces.EmployeeRepositoryInterface;
import pdir1736MV.repository.mock.EmployeeMock;
import pdir1736MV.validator.EmployeeValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddEmployeeTest {

    private EmployeeRepositoryInterface employeeRepository;
    private EmployeeController controller;
    private EmployeeValidator employeeValidator;

    @Before
    public void setUp() {
        employeeRepository = new EmployeeMock();
        controller = new EmployeeController(employeeRepository);
        employeeValidator = new EmployeeValidator();
    }

    @Test
    public void testRepositoryMock() {
        assertFalse(controller.getEmployeesList().isEmpty());
        assertEquals(6, controller.getEmployeesList().size());
    }

    @Test
    public void testAddNewEmployee() {
        Employee newEmployee = new Employee("ValidFirstName", "ValidLastName", "1910509055057", DidacticFunction.ASISTENT, "3000");
        assertTrue(employeeValidator.isValid(newEmployee));
        controller.addEmployee(newEmployee);
        assertEquals(7, controller.getEmployeesList().size());
        assertTrue(newEmployee.equals(controller.getEmployeesList().get(controller.getEmployeesList().size() - 1)));
    }

}
