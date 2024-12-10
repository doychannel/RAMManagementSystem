package tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Inputter {

    public  int inputQuantity(String mess) {
        /**
         * new ram must have quantity >=1 or else unavailable
         */
        
        int quantity;
        boolean validInput = false;
        do {
            quantity = Tools.inputInt(mess);
            if (quantity > 0) {
                validInput = true;
            } else {
                System.out.println("Invalid quantity. Must be a positive number to ensure availability.");
            }
        } while (!validInput);
        return quantity;
    }

    public  String inputMonthYear(String mess) {

        String monthYear;
        
        do {
            System.out.println(mess);
            monthYear = Tools.inputString("");
            
        } while (!isValidMonthYear(monthYear));

        return monthYear;
    }

    public  String inputType(String mess) {
        String type;
        boolean valid = false;
        do {
            type = Tools.inputString(mess);
            if (type.matches(".*\\d")) {
                valid = true;
            } else {
                System.out.println("Invalid type. Must match format DDRX.");
            }
        } while (!valid);
        return type;
    }

    public  String inputBus(String mess) {
        String bus;
        boolean valid = false;
        do {
            bus = Tools.inputString(mess);
            if (bus.matches("\\d+MHz")) {
                valid = true;
            } else {
                System.out.println("Invalid bus. Must include 'MHz' (e.g., 2400MHz).");
            }
        } while (!valid);
        return bus;
    }

    public  String updateInput(String mess, String currentValue) {
        String input = Tools.inputString(mess);
        return input.trim().isEmpty() ? currentValue : input;
    }

    public  int updateIntInput(String mess, int currentValue) {
        /**
         * update quantity(integer)
         */
        String input = Tools.inputString(mess);
        try {
            return input.trim().isEmpty() ? currentValue : Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, keeping current value.");
            return currentValue;
        }
    }

    public  String updateProductionDate(String mess, String currentValue) {
        /**
         * using isValidMonthYear to check
         */
        String input = Tools.inputString(mess);
        if (input.trim().isEmpty()) {
            return currentValue;
        }
        if (isValidMonthYear(input)) {
            return input;
        } else {
            System.out.println("Invalid format, keeping current date.");
            return currentValue;
        }
    }

    public  boolean isValidMonthYear(String monthYear) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        sdf.setLenient(false); // lenient parsing - false: dont allow 13 months, 32 days, etc.
        try {
            Date date = sdf.parse(monthYear);
            
            
            if (!sdf.format(date).equals(monthYear)) {
                throw new ParseException(monthYear + " is not a valid format.", 0);
            }
            return true;
        } catch (ParseException e) {
            System.out.println("Invalid format. Must match MM/yyyy.");
            return false;
        }
    }
}
