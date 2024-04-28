package Main.Database;

import java.util.Scanner;

public class Testing {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        StringBuilder description = new StringBuilder();

        System.out.println("Enter -1 to stop entering lines: ");
        String line = null;
        while(scanner.hasNext()){
            line = scanner.nextLine();
            if (line.equals("-1")){
                break;
            }
            description.append(line).append("\n");
        }

        System.out.println(description.toString());

    }
}
