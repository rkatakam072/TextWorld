package Commands;

import Main.World;

public class KillWumpus implements Command {

    @Override
    public void execute() {
        World world = World.getInstance();
        if (world.killWumpus()){
            System.out.println("you killed the wumpus");
        }else{
            System.out.println("nice try you didn't kill it");
        }
    }

    @Override
    public String displayCommandInfo() {
        return "KillWumpus ||  gets wumpus in room, if wumpus is in your room";
    }

    @Override
    public String getName() {
        return "KillWumpus";
    }
}
