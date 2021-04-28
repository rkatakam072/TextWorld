package Entities;

import Main.World;

public class Popstar extends GenericEntity {


    public Popstar(World.Room room) {
        super("popstar");
        this.description = "a really annoying person";

        setCurrentRoom(currentRoom);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public World.Room getRoom() {
        return currentRoom;
    }

    @Override
    public void move() {
        World.Room room = findAdjacentRoomWithPlayer();

        if (room != null) setCurrentRoom(room);
        else moveRandomly();
    }
}
