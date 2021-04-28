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
        displayCommands();
        System.out.println("-------------");

        while (true){
            System.out.println("You are currently in the " + world.getPlayer().getNameOfCurrentRoom());
            System.out.print("What do you want to do? > ");

            response = in.nextLine();
            response = response.trim();
            System.out.println("");

            Command command = commandParser.parser(response);
            command.execute();
        }

    }

    private static void displayCommands() {
        System.out.println("Possible commands:");
        System.out.println("go  \t\t|| goes to specified room");
        System.out.println("look \t\t\t\t|| displays neighboring rooms and everything in room  ");
        System.out.println("addroom || adds specified room with a 2-way connection to current room");
        System.out.println("quit || quits game");
        System.out.println("help || displays commands");
        System.out.println("getWumpus ||  gets wumpus in room");
        System.out.println("Take || can pickup item from room");
        System.out.println("showInventory || displays your inventory");
    }
}
