package Commands;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class CommandParser {

    private final ArrayList<Command> commands; // list of commands possible

    /**
     * constructs CommandParser
     */
    public CommandParser() {
        commands = getCommands();
    }


    /**
     * does the command
     * @param response the response/command of which to do
     * @return a command which you can execute
     */
    public Command parser(String response) {
        response = response.trim();

        for (Command command : commands) {
            if (command.getName().equalsIgnoreCase(response)) return command;
        }

        return null;
    }

    /**
     * gets the commands from the Commands Pakage
     * @return a list of Commands
     */
    private ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        File file = new File("src/Commands");
        for (String f : Objects.requireNonNull(file.list())) {
            try {
                Object object = Class.forName("Commands." + f.substring(0, f.length() - 5)).getConstructor().newInstance();
                if (object instanceof Command && !f.equals("Command.java")) {
                    Command command = (Command) object;
                    commands.add(command);
                }
            } catch (Exception e) {

            }
        }
        return commands;
    }

    /**
     * displays all the possible commands
     */
    public void displayCommands() {
        for (Command command : commands) {
            command.displayCommandInfo();
        }
    }

}
