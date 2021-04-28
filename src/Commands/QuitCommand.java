package Commands;

public class QuitCommand implements Command{
    @Override
    public void execute() {
        System.exit(0);
    }
}
