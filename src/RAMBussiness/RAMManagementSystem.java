package RAMBussiness;

import filehandle.FileHandler;
import main.Menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import model.RAM;

import tool.Tools;
import tool.Inputter;

public class RAMManagementSystem {

    private HashMap<String, RAM> ramItems;
    private FileHandler fileHandler;
    private String k = String.format("%15s %15s %15s %15s %12s %10s %10s",
            "RAM Code", "Type", "Bus", "Brand", "Quantity", "Date", "Active");

    public RAMManagementSystem(String fileName) {
        /**
         * constructor includes loadfile function, add to map ramItems
         */

        this.fileHandler = new FileHandler(fileName);
        this.ramItems = new HashMap<>();
        List<RAM> loadedRAM = fileHandler.loadFromFile();
        if (loadedRAM != null) {
            for (RAM ram : loadedRAM) {
                this.ramItems.put(ram.getCode(), ram);
            }
        } else {
            System.out.println("No data found");
        }
    }

    public void saveData() {
        List<RAM> ramList = new ArrayList<>(ramItems.values());
        fileHandler.saveToFile(ramList);
    }

    /**
     * add item
     */
    public void addItem() {
        String[] options = {"Add Ram Item", "Return to main menu"};
        int choice;
        Inputter Inputter = new Inputter();

        do {
            System.out.println("------------Add RAM Item------------");
            choice = Menu.getChoice(options);

            switch (choice) {
                case 1:
                    RAM ram = RAMCreation.createRAM(
                            Inputter.inputType("Enter type of RAM: "),
                            Inputter.inputBus("Enter bus speed of RAM: "),
                            Tools.inputStringNotNull("Enter brand of RAM: "),
                            Inputter.inputQuantity("Enter quantity of RAM: "),
                            Inputter.inputMonthYear("Enter date of production (MM/YYYY): "));

                    ram.setCode(generateCode(ram.getType()));
                    this.ramItems.put(ram.getCode(), ram);
                    System.out.println("RAM item added successfully.");

                    break;
                case 2:
                    System.out.println("Return to main menu....");
                    break;
                default:
                    System.out.println("Invalid input, please enter between 1 - 2.");
                    break;
            }

        } while (choice != options.length);
    }

    public List<RAM> toListandSort() {
        List<RAM> RAMList = new ArrayList<>(ramItems.values());
        /**
         * sort by type, bus speed, brand recursively turned to List
         */
        Collections.sort(RAMList, (RAM r1, RAM r2) -> {
            int typeCompare = r1.getType().compareTo(r2.getType());

            if (typeCompare == 0) {
                int bus1 = Integer.parseInt(r1.getBus().replaceAll("[^0-9]", ""));
                int bus2 = Integer.parseInt(r2.getBus().replaceAll("[^0-9]", ""));

                int busCompare = Integer.compare(bus2, bus1); //decreasing

                if (busCompare == 0) {
                    return r1.getBrand().compareTo(r2.getBrand());
                }
                return busCompare;
            }
            return typeCompare;
        });

        return RAMList;
    }

    public String generateCode(String type) {
        int maxOrder = 0;

        for (RAM ram : ramItems.values()) {
            if (ram.getType().equalsIgnoreCase(type)) {
                String[] parts = ram.getCode().split("_");
                if (parts.length == 2) {
                    int order = Integer.parseInt(parts[1]);
                    if (order > maxOrder) {
                        maxOrder = order;
                    }
                }
            }
        }
        String code = "RAM" + type + "_" + (maxOrder + 1);
        return code;
    }

    /**
     * update item if leave blank keep the current info, if wrong value, return
     * current info too
     *
     * @param code
     * @return
     */
    public boolean updateItem(String code) {
        Inputter Inputter = new Inputter();

        RAM ram = ramItems.get(code);
        boolean found = false;

        if (ram == null) {
            System.out.println("No RAM found with the given code.");
            return found;
        }

        System.out.println("Current RAM details:");
        System.out.println(k);
        System.out.println(ram);

        ram.setType(Inputter.updateInput("Enter new type of RAM : ", ram.getType()));

        ram.setCode(generateCode(ram.getType())); //reset Code since type changed

        ram.setBus(Inputter.updateInput("Enter new bus speed of RAM : ", ram.getBus()));

        ram.setBrand(Inputter.updateInput("Enter new brand of RAM : ", ram.getBrand()));

        ram.setQuantity(Inputter.updateIntInput("Enter new quantity of RAM : ", ram.getQuantity()));

        if (ram.getQuantity() <= 0) {
            System.out.println("Quantity<=0 -> item inactive ");
            ram.setFlag(false);
        } else {
            System.out.println("Quantity>0-> item activated");
            ram.setFlag(true);
        }

        ram.setProduction_month_year(Inputter.updateProductionDate("Enter new production date (MM/YYYY) : ",
                ram.getProduction_month_year()));

        found = true;

        return found;
    }

    /**
     * delete item based on status active
     *
     * @param code
     * @return
     */
    public boolean deleteItem(String code) {
        RAM ram = ramItems.get(code);
        boolean found = false;

        if (ram == null) {
            System.out.println("No such RAM found with the given code.");
            return found;
        }

        if (Tools.continueFunction("Do you wish to delete this ? y/n: ")) {
            ram.setFlag(false);
            found = true;
        }
        return found;
    }

    /**
     * show all item sorted by type, bus, brand recursively
     */
    public void showAllItems() {
        List<RAM> sortedRAMList = this.toListandSort();

        if (sortedRAMList.isEmpty()) {
            System.out.println("No RAM items available.");
            return;
        }

        System.out.println(k);

        sortedRAMList.stream().filter((item) -> (item.getFlag())).forEach((item) -> {
            System.out.println(item);
        });
//functional programming with item having flag = true, using lambda expression
    }

    /**
     * search ram with Function - pass with method reference
     */
    private void findAndPrint(String check, Function<RAM, String> extractor, String notFoundMessage) {
        boolean found = false;
        for (RAM ram : this.toListandSort()) {
            if (extractor.apply(ram).equalsIgnoreCase(check)) {
                System.out.println(ram.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println(notFoundMessage);
        }
    }

    public void search() {
        String options[] = {
            "Search by Type",
            "Search by Bus",
            "Search by Brand",
            "Exit to main menu"
        };

        int choice;
        do {
            System.out.println("------------Search sub-Menu------------");
            choice = Menu.getChoice(options);
            String check = Tools.inputString("Enter requirement(type,bus,brand) to check\n");
            boolean found = false;
            switch (choice) {
                case 1:
                    findAndPrint(check, RAM::getType, "Unfound Type");
                    break;
                case 2:
                    findAndPrint(check, RAM::getBus, "No such BUS info.");
                    break;
                case 3:
                    findAndPrint(check, RAM::getBrand, "No such Brand info.");
                    break;

                case 0:
                    System.out.println("Exit to main menu....");
                    break;
                default:
                    System.out.println("Invalid input, please enter between 0 - 3.");
                    break;
            }
        } while (choice != options.length);
    }

}
