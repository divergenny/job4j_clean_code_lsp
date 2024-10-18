package ru.job4j.ood.isp.menu;

import ru.job4j.ood.isp.menu.actions.*;
import ru.job4j.ood.isp.menu.input.ConsoleInput;
import ru.job4j.ood.isp.menu.input.Input;
import ru.job4j.ood.isp.menu.input.ValidateInput;

import java.util.ArrayList;
import java.util.List;

public class TodoApp {
    public static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action done");

    public void init(Input input, Menu menu, List<UserAction> userActions, ActionDelegate action, MenuPrinter printer) {
        boolean run = true;
        while (run) {
            this.showMenu(userActions);
            int select = input.askInt("Выберите: ");
            if (select < 0 || select >= userActions.size()) {
                System.out.println("Ошибка вводных данных, вы можете выбрать: 0 .. " + (userActions.size() - 1));
                continue;
            }
            UserAction userAction = userActions.get(select);
            run = userAction.execute(input, menu, action, printer);
        }
    }

    private void showMenu(List<UserAction> actions) {
        System.out.println("___|| Меню ||___ ");
        for (int index = 0; index < actions.size(); index++) {
            System.out.println(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) {
        Menu consoleMenu = new SimpleMenu();
        MenuPrinter printer = new Printer();
        Input input = new ValidateInput(new ConsoleInput());
        List<UserAction> userActions = new ArrayList<>();
        userActions.add(new CreateRootItemMenu());
        userActions.add(new CreateParentItemMenu());
        userActions.add(new CallItemAction());
        userActions.add(new ShowMenuOnConsole());
        new TodoApp().init(input, consoleMenu, userActions, DEFAULT_ACTION, printer);
    }
}
