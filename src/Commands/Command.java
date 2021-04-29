package Commands;

import java.util.Scanner;

public interface Command {


    /**
     * execute the command and its function
     */
    void execute();

    /**
     * returnns a String and Displays what this command does
     * @return a String  of information
     */
    String displayCommandInfo();

    /**
     * gets a response to a question the commands needs
     * @param question a question
     * @return whatever the player types
     */
    default String getResponse(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question + ">");
        return scanner.nextLine();
    }

    /**
     * @return the name/keyword for the command
     */
    String getName();
}
