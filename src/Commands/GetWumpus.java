package Commands;

import Main.Player;
import Main.World;

public class GetWumpus implements Command {

    @Override
    public void execute() {
        Player player = World.getInstance().getPlayer();
        player.getWumpus();
    }

    @Override
    public String displayCommandInfo() {
        return "getWumpus ||  gets wumpus in room, if wumpus is in your room";
    }

    @Override
    public String getName() {
        return "getWumpus";
    }
}
