package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean rslAdded = false;
        Optional<ItemInfo> parentItemInfoOptionalFound = findItem(parentName);
        Optional<ItemInfo> childItemInfoOptionalFound = findItem(childName);
        if (childItemInfoOptionalFound.isPresent()) {
            return rslAdded;
        }
        if (parentItemInfoOptionalFound.isEmpty()) {
            rslAdded = rootElements.add(new SimpleMenuItem(childName, actionDelegate));
        } else if (parentItemInfoOptionalFound.isPresent()) {
            rslAdded = parentItemInfoOptionalFound.get()
                    .menuItem
                    .getChildren()
                    .add(new SimpleMenuItem(childName, actionDelegate));
        }
        return rslAdded;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> rslAddedMenuItemInfo = Optional.empty();
        Optional<ItemInfo> foundItemInfo = findItem(itemName);
        if (foundItemInfo.isPresent()) {
            rslAddedMenuItemInfo = Optional.of(
                    new MenuItemInfo(foundItemInfo.get().getMenuItem(), foundItemInfo.get().getNumber()));
        }
        return rslAddedMenuItemInfo;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        DFSIterator simpleMenuIterator = new DFSIterator();
        return new Iterator<MenuItemInfo>() {
            @Override
            public boolean hasNext() {
                return simpleMenuIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo nextItemInfo = simpleMenuIterator.next();
                return new MenuItemInfo(nextItemInfo.getMenuItem(), nextItemInfo.getNumber());
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> foundItemOptional = Optional.empty();
        DFSIterator dfs = new DFSIterator();
        while (dfs.hasNext()) {
            ItemInfo itemInfoFromStack = dfs.next();
            MenuItem menuItemFromStack = itemInfoFromStack.getMenuItem();
            if (null == name) {
                foundItemOptional = Optional.empty();
            } else if (name.equals(menuItemFromStack.getName())) {
                foundItemOptional = Optional.of(itemInfoFromStack);
                break;
            }
        }
        return foundItemOptional;
    }

    @Override
    public String toString() {
        return "SimpleMenu{"
                + "rootElements=" + rootElements
                + '}';
    }

    private static class SimpleMenuItem implements MenuItem {
        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }

        @Override
        public String toString() {
            return "SimpleMenuItem{"
                    + "name='" + name + '\''
                    + ", children=" + children
                    + ", actionDelegate=" + actionDelegate
                    + '}';
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        private Deque<MenuItem> stack = new LinkedList<>();

        private Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++)
                        .concat("."));
            }
        }

        public Deque<MenuItem> getStack() {
            return stack;
        }

        public void setStack(Deque<MenuItem> stack) {
            this.stack = stack;
        }

        public Deque<String> getNumbers() {
            return numbers;
        }

        public void setNumbers(Deque<String> numbers) {
            this.numbers = numbers;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {

        private MenuItem menuItem;
        private String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }

        public MenuItem getMenuItem() {
            return menuItem;
        }

        public void setMenuItem(MenuItem menuItem) {
            this.menuItem = menuItem;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "ItemInfo{"
                    + "menuItem=" + menuItem
                    + ", number='" + number + '\''
                    + '}';
        }
    }
}
