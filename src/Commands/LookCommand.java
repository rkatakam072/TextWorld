package Commands;

import Main.Player;
import Main.World;


public class LookCommand implements Command {
    @Override
    public void execute() {
        Player player = World.getInstance().getPlayer();

        System.out.println(player.getNamesOfRooms());
        System.out.println(player.getNamesOfItemInRoom());
        System.out.println(player.getNamesOfEntitiesInRoom());
    }

    @Override
    public String displayCommandInfo() {
        return "look \t\t\t\t|| displays neighboring rooms and everything in room ";
    }

    @Override
    public String getName() {
        return "look";
    }
}
