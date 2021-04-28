package Entities;

import Main.World;

public interface Entity {
    public String getName();
    public String getDescription();
    public World.Room getRoom();
    public void move();
}
