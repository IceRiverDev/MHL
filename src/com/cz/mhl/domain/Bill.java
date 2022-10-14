package com.cz.mhl.domain;

/**
 * @author 刘洋
 * @date 2022/4/2  5:29 PM
 */

public class Bill {
    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer nums;
    private double money;
    private String billDate;
    private String state;
    private Integer diningTable;

    public Bill() {
    }

    public Bill(Integer id, String billId, Integer menuId, Integer nums, double money, String billDate, String state, Integer diningTable) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.nums = nums;
        this.money = money;
        this.billDate = billDate;
        this.state = state;
        this.diningTable = diningTable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getDiningTable() {
        return diningTable;
    }

    public void setDiningTable(Integer diningTable) {
        this.diningTable = diningTable;
    }

    @Override
    public String toString() {
        return " " + id + "\t\t\t" + " " + menuId + "\t\t\t\t" + " " + nums + "\t\t\t" + money
                + "\t\t\t" + diningTable + "\t\t    " + billDate + "\t\t\t  " + state;
    }
}
