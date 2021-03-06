package Commands;

import Main.Player;
import Main.World;

public class AddRoomCommand implements Command {
    @Override
    public void execute() {
        Player player = World.getInstance().getPlayer();

        String response = getResponse("what is the name of the room you want to add");
        World.getInstance().addRoom(response);

        String name = player.getNameOfCurrentRoom();
        World.getInstance().addDirectedEdge(name, response);
    }

    @Override
    public String displayCommandInfo() {
        return "addroom || adds specified room with a 2-way connection to current room";
    }

    @Override
    public String getName() {
        return "AddRoom";
    }
}
