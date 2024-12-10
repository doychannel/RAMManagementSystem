package main;

import tool.Tools;
import RAMBussiness.RAMManagementSystem;

public class Main {

    public static void main(String[] args) {
        final String FILE_NAME = "src/data/RAMModules.dat";
        
        RAMManagementSystem system = new RAMManagementSystem(FILE_NAME);

        int choice;
        String options[] = {"Add a RAM Item", "Search by Type/Bus/Brand", "Update Item Information", "Delete Item",
            "Show All Active Items", "Save Data to File", "Exit"};
        
        do {
            System.out.println(
                    "___________________________________\n"
                    + "     DuyDO RAM Management System  \n"
                    + "------------Main Menu------------\n");
            
            choice = Menu.getChoice(options);

            switch (choice) {
                case 1:
                    system.addItem();
                    break;

                case 2:
                    system.search();
                    break;

                case 3:
                    String code = Tools.inputString("Enter RAM code to update: ");
                    if (system.updateItem(code)) {
                        System.out.println("RAM item updated successfully.");
                    } else {
                        System.out.println("RAM item not found.");
                    }
                    break;

                case 4:
                    String id = Tools.inputString("Enter RAM code to delete: ");
                    if (system.deleteItem(id)) {
                        System.out.println("RAM item deleted successfully.");
                    } else {
                        System.out.println("RAM item not found.");
                    }
                    break;

                case 5:
                    System.out.println("All active RAM items: SORTED BY TYPE ASC - BUS DESC - BRAND ASC ");
                    system.showAllItems();
                    
                    break;

                case 6:

                    system.saveData();
                    System.out.println("SAVED ALL LIST!");
                    
                    break;

                case 7:

                    if (Tools.continueFunction("Do you want to save changes before exiting? (y/n): ")) {
                        system.saveData();
                        System.out.println("Data saved successfully. Exiting program...");

                    } else {
                        System.out.println("Exiting without saving...");
                    }

                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != options.length);
    }

}
