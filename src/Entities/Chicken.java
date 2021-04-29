package Entities;

import Main.World;

public class Chicken extends GenericEntity {


    /**
     * constructs the Chicken
     * @param room
     */
    public Chicken(World.Room room) {
        super("chicken");
        this.description = "a chicken";

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

    // moves randomly
    @Override
    public void move() {
        moveRandomly();
    }
}
