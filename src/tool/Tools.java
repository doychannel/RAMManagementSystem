package tool;

import java.util.Scanner;

public class Tools {
private static final Scanner sc = new Scanner(System.in);
   
public static String inputStringNotNull(String mess) {
        String result;
        do {
            System.out.print(mess);
            result = sc.nextLine().trim();
            if(result.isEmpty()){
                System.out.println("Input must not be empty.");
            }
        } while (result.isEmpty());
        return result.replaceAll("\\s+", "");
    }

    public static String inputString(String mess) {
        System.out.print(mess);
        String result = sc.nextLine().trim();
        result = result.replaceAll("\\s+", " ");
        return result;
    }

    
    public static int inputInt(String mess) {
        int result = 0;
        boolean valid = false;
        do {
            try {
                String input = inputString(mess);
                    result = Integer.parseInt(input);
                    valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter an integer number ");
            }
        } while (!valid);

        return result;
    }

    
    public static float inputFloat(String mess) {
        float result = 0;
        boolean valid = false;
        do {
            try {
                String input = inputString(mess);
                result = Float.parseFloat(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a float number.");
            }
        } while (!valid);

        return result;
    }

 
    public static boolean continueFunction(String prompt) {
        String input;
        do {
            input = Tools.inputString(prompt).trim().toLowerCase();
            switch (input) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    break;
            }
        } while (!input.equals("y") && !input.equals("n"));
        return false; 
    }

}
