package pdir1736MV.controller;

import pdir1736MV.exception.EmployeeException;
import pdir1736MV.model.Employee;
import pdir1736MV.repository.interfaces.EmployeeRepositoryInterface;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class EmployeeController {

    private EmployeeRepositoryInterface employeeRepository;

    public EmployeeController(EmployeeRepositoryInterface employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addEmployee(Employee employee) {
        employeeRepository.addEmployee(employee);
    }

    public List<Employee> getEmployeesList() {
        return employeeRepository.getEmployeeList();
    }

    public void modifyEmployee(Employee oldEmployee, Employee newEmployee) throws EmployeeException {
        employeeRepository.modifyEmployee(oldEmployee, newEmployee);
    }

    public void deleteEmployee(Employee employee) throws EmployeeException {
        employeeRepository.deleteEmployee(employee);
    }

    public List<Employee> getOrderedEmployees(String column) {
        return employeeRepository.getEmployeeByCriteria(column);
    }

    public Employee searchByCnp(String cnp) throws EmployeeException {
        return employeeRepository.getEmployeeByCnp(cnp);
    }
}
