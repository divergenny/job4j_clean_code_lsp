package ru.job4j.ood.isp.menu.actions;

import ru.job4j.ood.isp.menu.ActionDelegate;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.MenuPrinter;
import ru.job4j.ood.isp.menu.input.Input;

public class ShowMenuOnConsole implements UserAction {
    @Override
    public String name() {
        return "Показать меню";
    }

    @Override
    public boolean execute(Input input, Menu menu, ActionDelegate action, MenuPrinter printer) {
        System.out.println("=== Show menu ====");
        System.out.println(printer.print(menu));
        return true;
    }
}
