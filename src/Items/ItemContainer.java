package Items;

import java.util.List;

public interface ItemContainer {

    /**
     * adds item to list of items
     * @param item an Item
     */
    default void addItem(Item item) {
        List<Item> inventory = getListOfItems();
        inventory.add(item);
    }

    /**
     * makes new item and adds it to list
     * @param name name of item
     * @param desc description of item
     */
    default void addItem(String name, String desc) {
        List<Item> inventory = getListOfItems();
        inventory.add(new Item(name, desc));
    }

    /**
     * makes new item and adds it to list
     * @param name name of item
     */
    default void addItem(String name) {
        List<Item> inventory = getListOfItems();
        inventory.add(new Item(name, null));
    }

    /**
     * remove Item from list of items
     * @param name name of items
     * @return the item that was removed
     */
    default Item removeItem(String name) {
        List<Item> inventory = getListOfItems();
        name = name.trim();
        int index = getIndexFor(name);
        if (index == -1) return null;
        return inventory.remove(index);
    }

    /**
     * check if it contains an item
     * @param itemName the name of the item
     * @return true if list contains an item
     */
    default boolean contains(String itemName) {
        return (getIndexFor(itemName) != -1);
    }

    /**
     * @return an String of all the items in list
     */
    default String getInventoryString() {
        List<Item> inventory = getListOfItems();
        if (inventory.size() == 0) return "no items.";

        String out = "";
        for (Item i : inventory) {
            out += i.getName() + ", ";
        }
        return out.substring(0, out.length() - 2);
    }

    /**
     * gets the index for the story
     * @param name of string
     * @return an index for item(-1 if it doesn't exist)
     */
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



    /**
     * @return a list of items
     */
    List<Item> getListOfItems();
}