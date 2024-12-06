package RAMBussiness;

import model.RAM;

public class RAMCreation {

    public static RAM createRAM(String type, String bus, String brand, int quantity, String productionMonthYear) {
        return new RAM("", type, bus, brand, quantity, productionMonthYear, true);
    }
}
