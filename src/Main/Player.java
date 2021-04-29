package Main;


import Items.Item;
import Items.ItemContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements ItemContainer {

    private final ArrayList<Item> items; // items that the player is holding
    private World.Room currentRoom; // room that the player is currently in

    /**
     * constructs a Player
     * @param currentRoom the room player starts in
     */
    public Player(World.Room currentRoom) {
        items = new ArrayList<>();

        this.currentRoom = currentRoom;
    }


    /**
     * moves the player to a room
     * @param name name of the room player wants to move to
     * @return a boolean if the moving was successful
     */
    public boolean moveToRoom(String name) {
        World.Room neighbor = getRoom().getNeighbor(name);
        if (neighbor != null) {
            setCurrentRoom(neighbor);
            return true;
        }

        return false;
    }

    /**
     * sets the current room to another room
     * @param room the room you wnat to move to
     */
    private void setCurrentRoom(World.Room room) {
        this.currentRoom = room;
    }

    /**
     * gets the room the player is in
     * @return room that the player is in
     */
    private World.Room getRoom() {
        return currentRoom;
    }

    /**
     * gets the name of the neighbors rooms
     * @return a String of the room
     */
    public String getNamesOfRooms() {
        return getRoom().getNeighborNames();
    }

    /**
     * checks if the player is in a room
     * @param room room you want to check
     * @return true if player is in room
     */
    public boolean isInRoom(World.Room room) {
        return room == getRoom();
    }

    /**
     * @return the names of the items in the room
     */
    public String getNamesOfItemInRoom() {
        return getRoom().getItemsName();
    }

    /**
     * picks up item if in rom
     * @param string the name of the item
     */
    public void pickUpItem(String string) {
        Item item = getRoom().removeItem(string);
        if (item != null) addItem(item);

    }

    /**
     * drops an item in the room
     * @param string the name of the item that is going to be dropped
     */
    public void dropItem(String string) {
        Item item = removeItem(string);
        if (item != null) getRoom().addItem(item);
    }

    /**
     * @return a name of the currentRoom
     */
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

    /**
     * @return names of entities in the currentRoom
     */
    public String getNamesOfEntitiesInRoom() {
        return getRoom().getStringOfEntities();
    }

}
