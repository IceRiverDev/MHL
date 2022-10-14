package com.cz.mhl.domain;

/**
 * @author 刘洋
 * @date 2022/4/1  10:50 PM
 */
public class Employee {
    private Integer id;
    private String empId;
    private String name;
    private String password;
    private String job;

    public Employee() { // 反射需要
    }

    public Employee(Integer id, String empId, String name, String password, String job) {
        this.id = id;
        this.empId = empId;
        this.name = name;
        this.password = password;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
