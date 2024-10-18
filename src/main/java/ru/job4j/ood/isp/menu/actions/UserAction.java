package ru.job4j.ood.isp.menu.actions;

import ru.job4j.ood.isp.menu.ActionDelegate;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.MenuPrinter;
import ru.job4j.ood.isp.menu.input.Input;

public interface UserAction {
    String name();

    boolean execute(Input input, Menu menu, ActionDelegate action, MenuPrinter printer);
}
