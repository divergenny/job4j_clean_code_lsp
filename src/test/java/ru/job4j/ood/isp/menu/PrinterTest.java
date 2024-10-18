package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PrinterTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    void print() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        MenuPrinter menuPrinter = new Printer();
        String result = "1.Сходить в магазин"
                + "\n----1.1.Купить продукты"
                + "\n--------1.1.1.Купить хлеб"
                + "\n--------1.1.2.Купить молоко"
                + "\n2.Покормить собаку"
                + "\n";
        assertThat(menuPrinter.print(menu)).isEqualTo(result);
    }
}
