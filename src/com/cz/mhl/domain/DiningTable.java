package com.cz.mhl.domain;

/**
 * @author 刘洋
 * @date 2022/4/1  11:56 PM
 */
public class DiningTable {
    private Integer id;
    private String state;
    private String orderName;
    private String orderPhone;

    public DiningTable() {
    }

    public DiningTable(Integer id, String state, String orderName, String orderPhone) {
        this.id = id;
        this.state = state;
        this.orderName = orderName;
        this.orderPhone = orderPhone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    @Override
    public String toString() {
        return id + "\t\t\t\t\t" + state;
    }
}
