package Entities;

import Main.Player;
import Main.World;

public class Wumpus extends GenericEntity {

    private boolean canMove; // if the wumpus can move

    /**
     * constructs the wumpus
     * @param room were the wumpus initially is located
     */
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

    /**
     * stops the wumpus from moving
     * @param player makes sure player is in room
     * @return this instance to giving itself to a player
     */
    public Wumpus take(Player player) {
        if (player.isInRoom(getRoom())) {

            canMove = false;
            return this;
        }

        return null;
    }

    // moves away from player
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
                    nextRoom = getRoom().getRandomNeighbor();
                } while (nextRoom.containsPlayer());
            }
        }
    }
}
