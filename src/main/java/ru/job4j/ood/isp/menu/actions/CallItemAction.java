package ru.job4j.ood.isp.menu.actions;

import ru.job4j.ood.isp.menu.ActionDelegate;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.MenuPrinter;
import ru.job4j.ood.isp.menu.input.Input;

import java.util.Optional;

public class CallItemAction implements UserAction {
    @Override
    public String name() {
        return "Вызвать действие, привязанное к пункту меню.";
    }

    @Override
    public boolean execute(Input input, Menu menu, ActionDelegate action, MenuPrinter printer) {
        System.out.println("=== Call Item's Action ====");
        printer.print(menu);
        String itemSelected = input.askStr("Напишите полное название пункта меню, действие которого вы хотели бы вызвать: ");
        Optional<Menu.MenuItemInfo> menuItemInfoSelectedOpt = menu.select(itemSelected);
        if (menuItemInfoSelectedOpt.isPresent()) {
            menuItemInfoSelectedOpt.get().getActionDelegate().delegate();
            return true;
        }
        return false;
    }
}
