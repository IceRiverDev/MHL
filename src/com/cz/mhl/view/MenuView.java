package com.cz.mhl.view;

import com.cz.mhl.dao.EmployeeDAO;
import com.cz.mhl.domain.Bill;
import com.cz.mhl.domain.DiningTable;
import com.cz.mhl.domain.Employee;
import com.cz.mhl.domain.Menu;
import com.cz.mhl.service.BillService;
import com.cz.mhl.service.DiningTableService;
import com.cz.mhl.service.EmployeeService;
import com.cz.mhl.service.MenuService;
import com.cz.mhl.utils.Utility;

import java.util.List;

/**
 * @author 刘洋
 * @date 2022/4/1  9:19 PM
 */
public class MenuView {
    public static void main(String[] args) {
        new MenuView().mainMenu();
    }

    private static boolean stopLoop = false;
    private static final EmployeeService employeeService = new EmployeeService();
    private static final DiningTableService diningTableService = new DiningTableService();
    private static final MenuService menuService = new MenuService();
    private static final BillService billService = new BillService();

    public void mainMenu() {
        while (!stopLoop) {

            String choose = mainViewChoose();

            switch (choose) {
                case "1" -> {
                    loginSystem();
                }
                case "2" -> {
                    System.out.println("退出系统");
                    stopLoop = true;
                }
                default -> System.out.println("输入不正确，请重新输入");
            }
        }
    }

    private void startSecondaryMenu() {
        boolean stopSecondaryMenu = false;
        while (!stopSecondaryMenu) {

            String choose = secondaryViewChoose();
            switch (choose) {
                case "1" ->
                        //TODO 显示餐桌状态
                        listDiningTableState();
                case "2" ->
                        //TODO 预定餐桌
                        orderDiningTable();
                case "3" ->
                        //TODO 显示所有菜品
                        listAllMenus();
                case "4" ->
                        //TODO 点餐服务
                        orderMenu();
                case "5" ->
                        //TODO 查看账单
                        listBills();
                case "6" ->
                        //TODO 结账
                        payBill();
                case "9" -> {
                    stopSecondaryMenu = true;
                    stopLoop = true;
                    System.out.println("退出系统");
                }
                default -> System.out.println("输入不正确，请重新输入");
            }
        }
    }

    private static String secondaryViewChoose() {
        System.out.println("====================满汉楼二级菜单====================");
        System.out.println("\t\t\t 1 显示餐桌状态");
        System.out.println("\t\t\t 2 预定餐桌");
        System.out.println("\t\t\t 3 显示所有菜品");
        System.out.println("\t\t\t 4 点餐服务");
        System.out.println("\t\t\t 5 查看账单");
        System.out.println("\t\t\t 6 结账");
        System.out.println("\t\t\t 9 退出满汉楼");
        System.out.print("请输入你的选择: ");
        return Utility.readString(1);
    }

    private static String mainViewChoose() {
        System.out.println("====================满汉楼====================");
        System.out.println("\t\t\t 1 登录系统");
        System.out.println("\t\t\t 2 退出系统");
        System.out.print("请输入你的选择: ");
        return Utility.readString(1);
    }

    private static void orderDiningTable() {
        while (true) {
            System.out.println("====================预定餐桌====================");
            System.out.print("请选择要预定餐桌编号: ");
            int select = Utility.readInt(0);
            boolean exist = diningTableService.checkDiningTableIfExistsById(select);
            if (!exist) {
                System.out.println("输入的餐桌不存在，请重新输入");
                continue;
            }

            char c = Utility.readConfirmSelection();
            if (c == 'Y') {
                boolean empty = diningTableService.checkDiningTableIfEmptyById(select);
                if (!empty) {
                    System.out.println("输入的餐桌已经被预定或者正在就餐中，请重新选择需要预定的餐桌");
                    continue;
                }
            } else {
                System.out.println("====================取消预定餐桌====================");
                return;
            }

            System.out.print("预订人名字: ");
            String orderName = Utility.readString(5);
            System.out.print("预订人手机号: ");
            String orderPhone = Utility.readString(11);
            boolean orderSuccess = diningTableService.orderDiningTable(select, orderName, orderPhone, "已经预定");
            if (!orderSuccess) {
                System.out.println("预定失败, 请重新尝试预定");
                continue;
            }
            System.out.println("====================预定成功====================\n\n");
            return;
        }
    }

    private static void listDiningTableState() {
        List<DiningTable> diningList = diningTableService.getDiningList();
        System.out.println("餐桌编号\t\t\t\t状态");
        for (DiningTable diningTable : diningList) {
            System.out.println(diningTable);
        }
    }

    private void loginSystem() {
        System.out.print("请输入员工号: ");
        String empId = Utility.readString(50);
        System.out.print("请输入密码: ");
        String password = Utility.readString(50);
        boolean checkResult = employeeService.checkAccountPassword(empId, password);
        if (checkResult) {
            System.out.println("====================登录成功====================\n\n");
            // 启动二级菜单
            startSecondaryMenu();
        } else {
            System.out.println("====================登录失败====================\n\n");
        }
    }

    public void orderMenu() {
        while (true) {
            System.out.println("====================点餐服务====================");
            System.out.print("请选择点餐的桌号(-1 退出): ");
            int diningTableId = Utility.readInt();
            if (diningTableId == -1) return;

            System.out.print("请选择需要的菜品编号(-1 退出): ");
            int menuId = Utility.readInt();
            if (menuId == -1) return;

            System.out.print("请选择需要的菜品数量(-1 退出): ");
            int menuNums = Utility.readInt();
            if (menuNums == -1) return;
            System.out.print("确认是否点这个菜(Y/N): ");
            String c = Utility.readString(1);

            if (!menuService.checkMenuIfExistsById(menuId)) {
                System.out.println("选择的菜品编号不存在, 请重新选择");
                return;
            }

            if (!diningTableService.checkDiningTableIfExistsById(diningTableId)) {
                System.out.println("选择的桌号不存在, 请重新选择");
                return;
            }

            if (!diningTableService.checkDiningTableIfEmptyById(diningTableId)) {
                System.out.println("选择的桌号已被预定或正在就餐中，请重新选择");
                return;
            }

            if (c.equalsIgnoreCase("Y")) {
                boolean b = billService.orderMenu(menuId, menuNums, diningTableId);
                if (b) {
                    System.out.println("====================点餐成功====================\n\n");
                    return;
                } else {
                    System.out.println("====================点餐失败====================\n\n");
                }
            }
        }

    }

    private void listAllMenus() {
        System.out.println("菜品编号\t\t\t\t菜品名称\t\t\t\t种类\t\t\t价格");
        for (Menu menu : menuService.listMenu()) {
            System.out.println(menu);
        }
        System.out.println("====================显示完毕====================");
    }

    private void listBills() {
        System.out.println("编号\t\t\t菜品号\t\t\t菜品量\t\t金额\t\t\t   桌号  \t\t\t    日期\t\t\t\t\t   状态");
        for (Bill bill : billService.listBills()) {
            System.out.println(bill);
        }
        System.out.println("====================显示完毕====================\n\n");
    }

    private void payBill() {
        while (true) {
            System.out.println("====================结账服务====================");
            System.out.print("请选择要结账的餐桌编号(-1退出): ");
            int diningId = Utility.readInt();
            if (diningId == -1) {
                System.out.println("====================退出结账====================\n\n");
                return;
            }

            boolean diningTableIfExistsById = diningTableService.checkDiningTableIfExistsById(diningId);
            if (!diningTableIfExistsById) {
                System.out.println("====================结账的餐桌不存在，请重新输入====================");
                continue;
            }

            boolean checkBillIfPaidByDiningId = billService.checkBillIfPaidByDiningId(diningId);
            if (!checkBillIfPaidByDiningId) {
                System.out.println("====================该餐桌没有未结账单====================\n\n");
                return;
            }

            System.out.print("结账方式(现金/支付宝/微信/银行卡): ");
            String payMode = Utility.readString(10);

            System.out.print("确认结账(Y/N): ");
            String pay = Utility.readString(5);
            if (pay.equalsIgnoreCase("Y")) {
                boolean finishBillByDiningId = billService.finishBillByDiningId(diningId, payMode);
                if (finishBillByDiningId) {
                    System.out.println("====================结账成功, 欢迎下次光临====================\n\n");
                    return;
                }
                System.out.println("====================结账失败, 请重新结账====================");
            } else {
                System.out.println("====================退出结账====================\n\n");
            }
        }
    }
}

