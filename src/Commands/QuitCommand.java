package Commands;

public class QuitCommand implements Command{
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public String displayCommandInfo() {
        return "quit || quits game";
    }

    @Override
    public String getName() {
        return "Quit";
    }
}
