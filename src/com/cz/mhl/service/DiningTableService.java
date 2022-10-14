package com.cz.mhl.service;

import com.cz.mhl.dao.DiningTableDAO;
import com.cz.mhl.domain.DiningTable;

import java.util.List;

/**
 * @author 刘洋
 * @date 2022/4/1  11:55 PM
 */
public class DiningTableService {

    private static DiningTableDAO diningTableDAO = new DiningTableDAO();

    public List<DiningTable> getDiningList() {
        return diningTableDAO.queryMulti("select id, state from diningTable", DiningTable.class);
    }

    public DiningTable getDiningTableById(int id) {
        return diningTableDAO.querySingle("select id, state from diningTable where id = ?", DiningTable.class, id);
    }

    public boolean checkDiningTableIfEmptyById(int id) {
        DiningTable diningTable = getDiningTableById(id);
        return diningTable.getState().equals("空");
    }

    public boolean checkDiningTableIfExistsById(int id) {
        // true: exist false: not exist
        DiningTable diningTable = getDiningTableById(id);
        return diningTable != null;
    }

    public boolean orderDiningTable(int id, String orderName, String orderPhone, String state) {
        int result = diningTableDAO.update(
                "update diningTable set state = ?, orderName = ?, orderPhone = ? where id = ?",
                state,
                orderName,
                orderPhone,
                id
        );
        return result != 0;
    }

    public boolean updateDiningTableState(int id, String state) {
        int update = diningTableDAO.update("update diningTable set state = ? where id = ?", state, id);
        return update != 0;
    }

    public boolean updateDiningTableToFree(int id, String state) {
        return diningTableDAO.update("update diningTable set state = ?, orderName = '', orderPhone = '' where id = ?", state, id) > 0;
    }
}
