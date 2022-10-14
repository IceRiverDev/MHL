package com.cz.mhl.service;

import com.cz.mhl.dao.BillDAO;
import com.cz.mhl.domain.Bill;
import com.cz.mhl.domain.Menu;

import java.util.List;
import java.util.UUID;

/**
 * @author 刘洋
 * @date 2022/4/2  5:56 PM
 */
public class BillService {
    private BillDAO billDAO = new BillDAO();
    private MenuService menuService = new MenuService();
    private DiningTableService diningTableService = new DiningTableService();

    public String generateOrderId() {
        return UUID.randomUUID().toString();
    }

    public double calculateMenuPrice(int id, int nums) {
        Menu menuById = menuService.getMenuById(id);
        return menuById.getPrice() * nums;
    }

    public double calculateTotalPrice(int diningTable) {
        double totalPrice = 0;
        List<Bill> bills = billDAO.queryMulti("select * from bill where diningTable = ?", Bill.class, diningTable);
        for (Bill bill : bills) {
            totalPrice += calculateMenuPrice(bill.getId(), bill.getNums());
        }
        return totalPrice;
    }

    public boolean orderMenu(int menId, int nums, int diningTableId) {
        String billId = generateOrderId();
        int updateResult = billDAO.update("insert into bill values(null, ?, ?, ?, ?, ?, now(), '未结账', null)", billId, menId, nums, calculateMenuPrice(menId, nums), diningTableId);

        if (updateResult <= 0) {
            return false;
        }

        return diningTableService.updateDiningTableState(diningTableId, "就餐中");
    }

    public List<Bill> listBills() {
        return billDAO.queryMulti("select * from bill", Bill.class);
    }

    public boolean checkBillIfPaidByDiningId(int diningId) {
        Bill bill = billDAO.querySingle("select state from bill where diningTable = ?", Bill.class, diningId);
        if (bill == null) {
            return false;
        }

        return bill.getState().equals("未结账");
    }

    public boolean finishBillByDiningId(int diningId, String paymentMode) {
        int update = billDAO.update("update bill set state = ?, payDate = now() where diningTable = ?", paymentMode, diningId);
        if (update <= 0) {
            return false;
        }

        return diningTableService.updateDiningTableToFree(diningId, "空");
    }
}
