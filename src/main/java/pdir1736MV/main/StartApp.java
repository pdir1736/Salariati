package pdir1736MV.main;

import pdir1736MV.controller.EmployeeController;
import pdir1736MV.enumeration.DidacticFunction;
import pdir1736MV.model.Employee;
import pdir1736MV.repository.implementations.EmployeeImpl;
import pdir1736MV.repository.interfaces.EmployeeRepositoryInterface;
import pdir1736MV.repository.mock.EmployeeMock;
import pdir1736MV.validator.EmployeeValidator;
import pdir1736MV.view.EmployeeView;

//functionalitati
//i.	 adaugarea unui nou angajat (nume, prenume, CNP, functia didactica, salariul de incadrare);
//ii.	 modificarea functiei didactice (asistent/lector/conferentiar/profesor) a unui angajat;
//iii.	 afisarea salariatilor ordonati descrescator dupa salariu si crescator dupa varsta (CNP).
public class StartApp {

    public static void main(String[] args) {

//        EmployeeRepositoryInterface employeesRepository = new EmployeeMock();
        EmployeeRepositoryInterface employeesRepository = new EmployeeImpl();
        EmployeeController employeeController = new EmployeeController(employeesRepository);
        EmployeeView employeeView = new EmployeeView(employeeController);
        employeeView.start();

//        for (Employee _employee : employeeController.getEmployeesList())
//            System.out.println(_employee.toString());
//        System.out.println("-----------------------------------------");
//
//        Employee employee = new Employee("FirstName", "LastName", "1234567894321", DidacticFunction.ASISTENT, "2500");
//        employeeController.addEmployee(employee);
//
//        for (Employee _employee : employeeController.getEmployeesList())
//            System.out.println(_employee.toString());
//
//        EmployeeValidator validator = new EmployeeValidator();
//        System.out.println(validator.isValid(new Employee("FirstName", "LastName", "1234567894322", DidacticFunction.TEACHER, "3400")));

    }

}
