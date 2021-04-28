package Commands;

import Main.World;

public class ShowInventoryCommand implements Command{

    @Override
    public void execute() {
        System.out.println(World.getInstance().getPlayer().getInventoryString());
    }
}
