package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class Printer implements MenuPrinter {
    @Override
    public String print(Menu menu) {
        StringBuilder result = new StringBuilder();
        Menu.MenuItemInfo menuItemInfo;
        Iterator<Menu.MenuItemInfo> menuIterator = menu.iterator();
        while (menuIterator.hasNext()) {
            menuItemInfo = menuIterator.next();
            String menuItemInfoNumber = menuItemInfo.getNumber();
            String indent = identCreating(countDigits(menuItemInfoNumber), "----");
            result.append(indent.concat(menuItemInfoNumber).concat(menuItemInfo.getName())).append("\n");
        }
        return result.toString();
    }

    public int countDigits(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    public String identCreating(int countDigit, String typeIdent) {
        StringBuilder builder = new StringBuilder();
        if (countDigit > 1) {
            for (int i = 1; i < countDigit; i++) {
                builder.append(typeIdent);
            }
        }
        return builder.toString();
    }
}
