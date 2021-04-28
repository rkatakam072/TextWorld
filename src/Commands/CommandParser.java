package Commands;

public class CommandParser {


    public Command parser(String response){
        String commandWord = getFirstWord(response);

        if (commandWord.equalsIgnoreCase("addroom")) return new AddRoomCommand();
        else if (commandWord.equalsIgnoreCase("Drop")) return new DropCommand();
        else if (commandWord.equalsIgnoreCase("go")) return new GoCommand();
        else if (commandWord.equalsIgnoreCase("look")) return new LookCommand();
        else if (commandWord.equalsIgnoreCase("Quit")) return new QuitCommand();
        else if (commandWord.equalsIgnoreCase("ShowInventory")) return new ShowInventoryCommand();
        else if (commandWord.equalsIgnoreCase("Take")) return new TakeCommand();

        return null;
    }

    private String getFirstWord(String response) {
        return response.split(" ")[0];
    }

}
