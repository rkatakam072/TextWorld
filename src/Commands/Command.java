package Commands;

import java.util.Scanner;

public interface Command {

    void execute();

    default String getResponse(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question + ">");
        return scanner.nextLine();
    }
}
