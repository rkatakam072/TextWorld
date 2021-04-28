package Commands;

import Main.World;

public class TakeCommand implements Command{
    @Override
    public void execute() {
        System.out.println(World.getInstance().getPlayer().getNamesOfItemInRoom());
        String response = getResponse("what item do you want to take");

        World.getInstance().getPlayer().pickUpItem(response);
    }

    @Override
    public String displayCommandInfo() {
        return "Take || can pickup item from room";
    }

    @Override
    public String getName() {
        return "take";
    }
}
