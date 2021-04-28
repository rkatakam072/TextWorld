package Main;

import Items.Item;
import Items.ItemContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements ItemContainer {

    private final ArrayList<Item> items;
    private World.Room currentRoom;

    public Player(World.Room currentRoom) {
        items = new ArrayList<>();

        this.currentRoom = currentRoom;
    }


    public boolean moveToRoom(String name) {
        World.Room neighbor = getRoom().getNeighbor(name);
        if (neighbor != null) {
            setCurrentRoom(neighbor);
            return true;
        }

        return false;
    }

    private void setCurrentRoom(World.Room room) {
        this.currentRoom = room;
    }

    private World.Room getRoom() {
        return currentRoom;
    }

    public String getNamesOfRooms() {
        return getRoom().getNeighborNames();
    }

    public boolean isInRoom(World.Room room) {
        return room == getRoom();
    }

    public String getNamesOfItemInRoom() {
        return getRoom().getItemsName();
    }

    public void pickUpItem(String string) {
        Item item = getRoom().removeItem(string);
        if (item != null) addItem(item);

    }

    public void dropItem(String string) {
        Item item = removeItem(string);
        if (item != null) getRoom().addItem(item);
    }

    public String getNameOfCurrentRoom() {
        return getRoom().getName();
    }

    @Override
    public List<Item> getListOfItems() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (element.getClassName().equals("ItemContainer"))
            return items;

        return Collections.unmodifiableList(items);
    }

    public String getNamesOfEntitiesInRoom() {
        return getRoom().getStringOfEntities();
    }
}
