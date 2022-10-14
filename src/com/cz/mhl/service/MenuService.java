package com.cz.mhl.service;

import com.cz.mhl.dao.MenuDAO;
import com.cz.mhl.domain.Menu;

import java.util.List;

/**
 * @author 刘洋
 * @date 2022/4/2  5:07 PM
 */
public class MenuService {
    private MenuDAO menu = new MenuDAO();

    public List<Menu> listMenu() {
        return menu.queryMulti("select id, name, type, price from menu", Menu.class);
    }

    public Menu getMenuById(int menuId) {
        return menu.querySingle("select * from menu where id = ?", Menu.class, menuId);
    }

    public boolean checkMenuIfExistsById(int menId) {
        return getMenuById(menId) != null;
    }
}

