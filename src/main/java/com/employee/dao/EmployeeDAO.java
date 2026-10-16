package com.employee.dao;

import com.employee.entity.Employee;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.SelectionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
public class EmployeeDAO {

    @Autowired
    SessionFactory sessionFactory;


    public void saveEmployee(Employee emp) {
        Session session = getSession();
        session.persist(emp);
        System.out.println("Successfully inserted");
    }

    public void deleteEmployee(int id) {
        Session session = getSession();
        Employee employee = session.find(Employee.class, id);
        if (employee != null) {
            session.remove(employee);
            System.out.print("Employee deleted ");

        }
        else
            System.out.println("We cannot find the employee to delete ");
    }

    public void updateEmployee(double salary, int id) {
        Session session = getSession();
        Employee employee = session.find(Employee.class, id);
        if (employee == null)
            System.out.println("employee is not there");
        else {
            employee.setSalary(salary);
            System.out.println("salary updated..");
        }

    }

    public List<Employee> getAllEmployees() {
        Session session = getSession();
        SelectionQuery<Employee> empList = session.createSelectionQuery("From Employee", Employee.class);
        List<Employee> list = empList.list();
        return list;

    }

    public Employee getEmployeeById(int id) {
        Session session = getSession();
        Employee employee = session.find(Employee.class, id);
        return employee;
    }

    //    get session
    public Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
}
