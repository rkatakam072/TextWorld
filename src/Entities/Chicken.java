package Entities;

import Main.World;

public class Chicken extends GenericEntity {


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

    @Override
    public void move() {
        moveRandomly();
    }
}
