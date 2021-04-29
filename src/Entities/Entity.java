package Entities;

import Main.World;

public interface Entity {
    public String getName(); //return name

    public String getDescription(); // returns description

    public World.Room getRoom(); //return room

    public void move();// each entity should be moving
}
