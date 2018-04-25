package pdir1736MV.repository.implementations;


import pdir1736MV.exception.EmployeeException;
import pdir1736MV.model.Employee;
import pdir1736MV.repository.interfaces.EmployeeRepositoryInterface;
import pdir1736MV.validator.EmployeeValidator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class EmployeeImpl implements EmployeeRepositoryInterface {

    private final String employeeDBFile = "employeeDB/employees.txt";
    private EmployeeValidator employeeValidator = new EmployeeValidator();

    @Override
    public boolean addEmployee(Employee employee) {
        if (employeeValidator.isValid(employee)) {
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(employeeDBFile, true));
                bw.write(employee.toString());
                bw.newLine();
                bw.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean addEmployee(Employee employee, String file) {
        if (employeeValidator.isValid(employee)) {
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(file, true));
                bw.write(employee.toString());
                bw.newLine();
                bw.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void deleteEmployee(Employee oldEmployee) throws EmployeeException {

        BufferedReader br = null;
        String tempFile = employeeDBFile.concat("temp");
        File inputFile = new File(employeeDBFile);
        File tempFileObj = new File(tempFile);
        try {
            br = new BufferedReader(new FileReader(employeeDBFile));
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                Employee employee = new Employee();
                try {
                    employee = employee.getEmployeeFromString(line, counter);
                    if (!employee.equals(oldEmployee)) {
                        addEmployee(employee, tempFile);
                    }
                } catch (EmployeeException ex) {
                    System.err.println("Error while reading: " + ex.toString());
                }
            }
            br.close();
            boolean successful = tempFileObj.renameTo(inputFile);
        } catch (IOException e) {
            System.err.println("Error while reading: " + e);
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Error while closing the file: " + e);
                }
        }
    }

    @Override
    public void modifyEmployee(Employee oldEmployee, Employee newEmployee) throws EmployeeException {
        File inputFile = new File(employeeDBFile);
        Path path = inputFile.toPath();
        List<String> fileContent = null;
        try {
            fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(oldEmployee.toString())) {
                    fileContent.set(i, newEmployee.toString());
                    Files.write(path, fileContent, StandardCharsets.UTF_8);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = new ArrayList<Employee>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(employeeDBFile));
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                Employee employee = new Employee();
                try {
                    employee = employee.getEmployeeFromString(line, counter);
                    employeeList.add(employee);
                } catch (EmployeeException ex) {
                    System.err.println("Error while reading: " + ex.toString());
                }
            }
        } catch (IOException e) {
            System.err.println("Error while reading: " + e);
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Error while closing the file: " + e);
                }
        }

        return employeeList;
    }

    @Override
    public List<Employee> getEmployeeByCriteria(String criteria) {
        List<Employee> employeeList = getEmployeeList();
        switch (criteria) {
            case "salary": {
                employeeList.sort(Comparator.comparing(Employee::getSalary));
                break;
            }
            case "age": {
                employeeList.sort(Comparator.comparing(e -> e.getCnp().substring(1, 6)));
                break;
            }
        }

        return employeeList;
    }

    @Override
    public Employee getEmployeeByCnp(String cnp) throws EmployeeException {
        for (Employee employee : getEmployeeList()) {
            if (Objects.equals(employee.getCnp(), cnp))
                return employee;
        }

        throw new EmployeeException("Angajatul nu exista");
    }

    public Employee getEmployeeByCNP(String cnp) {
        List<Employee> employeeList = this.getEmployeeList();
        for (Employee _employee : employeeList) {
            if (_employee.getCnp().equals(cnp)) {
                return _employee;
            }
        }
        return null;
    }

}
