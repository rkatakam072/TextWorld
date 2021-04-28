package Commands;

import Main.Player;
import Main.World;

public class GetWumpus implements Command {

    @Override
    public void execute() {
        Player player = World.getInstance().getPlayer();
        player.getWumpus();
    }
}
