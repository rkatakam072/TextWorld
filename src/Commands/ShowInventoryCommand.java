package Commands;

import Main.World;

public class ShowInventoryCommand implements Command{

    @Override
    public void execute() {
        System.out.println(World.getInstance().getPlayer().getInventoryString());
    }

    @Override
    public String displayCommandInfo() {
        return "showInventory || displays your inventory";
    }

    @Override
    public String getName() {
        return "showInventory";
    }
}
