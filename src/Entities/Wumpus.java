package Entities;

import Main.Player;
import Main.World;

public class Wumpus extends GenericEntity {

    private boolean canMove;

    //todo: fix taking Wumpuses
    public Wumpus(World.Room room) {
        super(" wumpus");
        this.description = " a wierd thing";

        canMove = true;

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

    public Wumpus take(Player player) {
        if (player.isInRoom(getRoom())) {

            canMove = false;
            return this;
        }

        return null;
    }

    @Override
    public void move() {

        if (canMove) {
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
}
