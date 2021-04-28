package Commands;

import java.util.Scanner;

public interface Command {

    String name = "";
    String Description = "";

    void execute();

    String displayCommandInfo();

    default String getResponse(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question + ">");
        return scanner.nextLine();
    }

    String getName();
}
