package Commands;


import Main.World;

public class DropCommand implements Command {
    @Override
    public void execute() {
        String response = getResponse("what do you want to drop");
        World.getInstance().getPlayer().dropItem(response);


    }
}
