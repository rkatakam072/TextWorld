package Items;

public class Item {

    private final String name, description; // basic things all items have

    /**
     * constructs an Item
     * @param name name of the item
     * @param description description of item if given
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return the name of the item
     */
    public String getName() {
        return name;
    }
}
