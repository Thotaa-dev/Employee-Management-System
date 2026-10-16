package com.employee.main;

import com.employee.config.AppConfig;
import com.employee.dao.EmployeeDAO;
import com.employee.entity.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        EmployeeDAO employeeDAO = context.getBean("employeeDAO", EmployeeDAO.class);
        int option;
        do {
            System.out.println("===== Employee Management System =====\n"
                    + "1. Save Employee\n"
                    + "2. Get Employee By ID\n"
                    + "3. Get All Employees\n"
                    + "4. Update Employee\n"
                    + "5. Delete Employee\n"
                    + "6. Exit\n"
                    + "\nEnter your choice: ");

            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.print("Enter name :- ");
                    String name = sc.nextLine();
                    System.out.print("Enter emaill :- ");
                    String email = sc.nextLine();
                    System.out.print("Enter department :- ");
                    String department = sc.nextLine();
                    System.out.print("Enter Salary :- ");
                    double salary = sc.nextDouble();
                    Employee emp = new Employee(name, email, department, salary);
                    employeeDAO.saveEmployee(emp);
                    break;

                case 2:
                    System.out.print("Enter Employee ID :- ");
                    int id = sc.nextInt();
                    Employee employeeById = employeeDAO.getEmployeeById(id);
                    if (employeeById == null)
                        System.out.println("employee is not available");
                    else
                        System.out.println(employeeById);
                    break;

                case 3:
                    List<Employee> allEmployees = employeeDAO.getAllEmployees();

                    if (allEmployees.isEmpty()) {
                        System.out.println("No employees found");
                    } else {
                        for (Employee e : allEmployees) {
                            System.out.println(e);
                        }
                    }
                    break;

                case 4:
                    System.out.print("Enter Employee ID :- ");
                    int id1 = sc.nextInt();
                    System.out.print("Enter salary:- ");
                    double salary1 = sc.nextDouble();
                    employeeDAO.updateEmployee(salary1, id1);
                    break;

                case 5:
                    System.out.print("Enter Employee ID :- ");
                    int id2 = sc.nextInt();
                    employeeDAO.deleteEmployee(id2);
                    break;

                case 6:
                    System.out.println("Thank you for using Employee Management System!");
                    break;

                default:
                    System.out.println("Invalid option.. choose 1 to 6");
            }
        } while (option != 6);
    }
}
