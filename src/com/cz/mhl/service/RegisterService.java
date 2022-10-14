package com.cz.mhl.service;

import com.cz.mhl.dao.EmployeeDAO;
import com.cz.mhl.dao.EmployeeInfoDAO;
import com.cz.mhl.domain.Employee;
import com.cz.mhl.domain.EmployeeInfo;

/**
 * @author 刘洋
 * @date 2022/4/3  12:01 PM
 */
public class RegisterService {
    private static EmployeeInfoDAO employeeInfoDAO = new EmployeeInfoDAO();
    private static EmployeeDAO employeeDAO = new EmployeeDAO();

    public boolean registerJob(String... info) {
        int update = employeeDAO.update("insert into employee values (null, ?, ?, ?, ?)", Employee.class, info);
        return update > 0;
    }

    public boolean registerPersonalInfo(String... info) {
        int update = employeeInfoDAO.update("insert into employeeInfo values (null, ?, ?, ?, ?, ?, ?, ?, ?)", EmployeeInfo.class, info);
        return update > 0;
    }
}
