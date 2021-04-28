package Entities;

import Main.World;

public abstract class GenericEntity implements Entity {
    protected String name, description;
    protected World.Room currentRoom;

    public GenericEntity(String name) {
        this.name = name;
        this.description = "";
        this.currentRoom = null;
    }

    public abstract void move();


    public void setCurrentRoom(World.Room room) {
        if (currentRoom == null) this.currentRoom = room;
        else if (currentRoom.hasNeighbor(room)) this.currentRoom = room;
    }

    protected void moveRandomly() {
        setCurrentRoom(getRoom().getRandomRoom());
    }

    protected World.Room findAdjacentRoomWithPlayer() {

        if (getRoom().getNeighbors().size() == 0) return getRoom();
        for (World.Room neighbor : getRoom().getNeighbors()) {
            if (neighbor.containsPlayer()) {
                return neighbor;
            }
        }
        return null;
    }
}
