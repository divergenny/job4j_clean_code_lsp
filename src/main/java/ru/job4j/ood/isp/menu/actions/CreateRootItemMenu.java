package ru.job4j.ood.isp.menu.actions;

import ru.job4j.ood.isp.menu.ActionDelegate;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.MenuPrinter;
import ru.job4j.ood.isp.menu.input.Input;

public class CreateRootItemMenu implements UserAction {
    @Override
    public String name() {
        return "Создать родительский элемент";
    }

    @Override
    public boolean execute(Input input, Menu menu, ActionDelegate action, MenuPrinter printer) {
        System.out.println("=== Create a new Root item ====");
        String rootName = input.askStr("Напишите имя родителя: ");
        return menu.add(Menu.ROOT, rootName, action);
    }
}
