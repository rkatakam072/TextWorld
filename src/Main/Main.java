package Main;

import Commands.Command;
import Commands.CommandParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        World world = World.getInstance();

        world.moveEntities();

        CommandParser commandParser = new CommandParser();

        String response = "";
        Scanner in = new Scanner(System.in);
        commandParser.displayCommands();
        System.out.println("-------------");

        while (true){
            System.out.println("You are currently in the " + world.getPlayer().getNameOfCurrentRoom());
            System.out.print("What do you want to do? > ");

            response = in.nextLine();
            response = response.trim();
            System.out.println("");

            Command command = commandParser.parser(response);
            if (command != null) command.execute();
        }
    }
}
