package com.cz.mhl.service;

import com.cz.mhl.dao.EmployeeDAO;
import com.cz.mhl.domain.Employee;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 刘洋
 * @date 2022/4/1  10:55 PM
 */
public class EmployeeService {
    private EmployeeDAO empDAO = new EmployeeDAO();
    private static final HashMap<String, Employee> cachedEmployees = new HashMap<>();

    public boolean checkAccountPassword(String empId, String password) {
        Employee employee = cachedEmployees.get(empId);
        if (employee != null) {
            return true;
        }
        employee = empDAO.querySingle("select * from employee where empId = ? and password = md5(?)", Employee.class, empId, password);
        if (employee != null) {
            cachedEmployees.put(employee.getEmpId(), employee);
            return true;
        }
        return false;
    }

    public static Employee getEmployeeFast(String empId) {
        return cachedEmployees.get(empId);
    }

    public static void clearCache() {
        cachedEmployees.clear();
    }

    public static HashMap<String, Employee> getCacheEmployees() {
        return cachedEmployees;
    }
}
