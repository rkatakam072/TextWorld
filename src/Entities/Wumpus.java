package Entities;

import Main.World;

public class Wumpus extends GenericEntity {

    public Wumpus(World.Room room) {
        super(" wumpus");
        this.description = " a wierd thing";

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

        World.Room room = findAdjacentRoomWithPlayer();

        if (getRoom().containsPlayer()) {
            moveRandomly();
        } else if (room != null) {
            if (getRoom().getNeighbors().size() == 1) return;

            World.Room nextRoom;

            do {
                nextRoom = getRoom().getRandomRoom();
            } while (nextRoom.containsPlayer());
        }
    }
}
