package bg.tusofia;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            int option, line1Number, line2Number, word1Number, word2Number;

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter file path: ");
            String filePath = scanner.nextLine();

            FileManipulator manipulator = new FileManipulator(filePath);

            System.out.println("1) Replace two selected lines");
            System.out.println("2) Replace two words in two selected lines");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();

            if (option == 1) {
                System.out.print("Select first line number: ");
                line1Number = scanner.nextInt();

                System.out.print("Select second line number: ");
                line2Number = scanner.nextInt();

                manipulator.replaceLines(line1Number - 1, line2Number - 1);
            } else if (option == 2) {
                System.out.print("Select first line number: ");
                line1Number = scanner.nextInt();

                System.out.print("Select first word number: ");
                word1Number = scanner.nextInt();

                System.out.print("Select second line number: ");
                line2Number = scanner.nextInt();

                System.out.println("Select second word number: ");
                word2Number = scanner.nextInt();

                manipulator.replaceWords(line1Number - 1, word1Number - 1, line2Number - 1, word2Number - 1);
            } else {
                throw new InvalidOptionException();
            }


        } catch (IOException | InvalidOptionException e) {
            System.out.println("An error has occurred:");
            System.out.println(e.getMessage());
        }
    }
}
