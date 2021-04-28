package Commands;
import Main.World;

public class GoCommand implements Command{


    @Override
    public void execute() {
        String response = getResponse("what room do you want to go to");
        World.getInstance().getPlayer().moveToRoom(response);
    }

    @Override
    public String displayCommandInfo() {
        return "go  \t\t|| goes to specified room";
    }

    @Override
    public String getName() {
        return "Go";
    }
}
