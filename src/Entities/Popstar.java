package Entities;

import Main.World;

public class Popstar extends GenericEntity {


    /**
     * constructs a popstar
     * @param room room where the popstar starts
     */
    public Popstar(World.Room room) {
        super("popstar");
        this.description = "a really annoying person";

        setCurrentRoom(room);
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

    // moves to player or randomly if  player is not in neighboring room
    @Override
    public void move() {
        World.Room room = findAdjacentRoomWithPlayer();

        if (room != null) setCurrentRoom(room);
        else moveRandomly();
    }
}
