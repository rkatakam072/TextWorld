package Items;

import java.util.List;

public interface ItemContainer {

    default void addItem(Item item) {
        List<Item> inventory = getList();
        inventory.add(item);
    }

    default void addItem(String name, String desc) {
        List<Item> inventory = getList();
        inventory.add(new Item(name, desc));
    }

    default void addItem(String name) {
        List<Item> inventory = getList();
        inventory.add(new Item(name, null));
    }

    default Item removeItem(String name) {
        List<Item> inventory = getList();
        name = name.trim();
        int index = getIndexFor(name);
        if (index == -1) return null;
        return inventory.remove(index);
    }

    default boolean contains(String itemName) {
        return (getIndexFor(itemName) != -1);
    }

    default String getInventoryString() {
        List<Item> inventory = getList();
        if (inventory.size() == 0) return "no items.";

        String out = "";
        for (Item i : inventory) {
            out += i.getName() + ", ";
        }
        return out.substring(0, out.length() - 2);
    }

    private int getIndexFor(String name) {
        List<Item> inventory = null;
        try {
            inventory = getListOfItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int index = 0; index < inventory.size(); index++) {
            if (inventory.get(index).getName().equals(name)) {
                return index;
            }
        }
        return -1;
    }

    private List<Item> getList() {
        List<Item> list = null;
        try {
            list = getListOfItems();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    List<Item> getListOfItems();
}