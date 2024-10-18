package ru.job4j.ood.isp.menu.actions;

import ru.job4j.ood.isp.menu.ActionDelegate;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.MenuPrinter;
import ru.job4j.ood.isp.menu.input.Input;

public class CreateParentItemMenu implements UserAction {
    @Override
    public String name() {
        return "Создать элемент родителя";
    }

    @Override
    public boolean execute(Input input, Menu menu, ActionDelegate action, MenuPrinter printer) {
        System.out.println("=== Create a new Parent's item ====");
        System.out.println("Существует следующее меню:");
        printer.print(menu);
        String parentName = input.askStr("Напишите полное имя выбранного вами родителя:: ");
        String childName = input.askStr("Напишите имя дочернего элемента: ");
        return menu.add(parentName, childName, action);
    }
}
