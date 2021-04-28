package Commands;


import Main.World;

public class DropCommand implements Command {
    @Override
    public void execute() {
        String response = getResponse("what do you want to drop");
        World.getInstance().getPlayer().dropItem(response);


    }

    @Override
    public String displayCommandInfo() {
       return  "Drop || drops item in your inventory, in the room your in";
    }

    @Override
    public String getName() {
        return "Drop";
    }
}
