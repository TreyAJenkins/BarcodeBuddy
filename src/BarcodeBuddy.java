import GUI.EquipmentListing;
import GUI.Prompt;

import java.util.ArrayList;

public class BarcodeBuddy {

    private static ArrayList<Location> locations = new ArrayList<>();
    private static boolean isRunning = true;

    private static void scanMode(Prompt prompt) {
        prompt.clear();

        //BarcodeScanner scanner = new BarcodeScanner();
        BarcodeScanner scanner = new FakeBarcodeScanner();
        prompt.prompt("Please scan a location barcode", "Open scanner");
        String scanned = scanner.scan();
        // TODO: Check if scanned is empty

        int index = 0;
        if (locations.contains(scanned)) {
            index = locations.indexOf(scanned);
            System.out.println("Location already in DB");
        } else {
            System.out.println("New Location");
            locations.add(new Location(scanned));
            index = locations.size()-1;
        }

        System.out.println(locations);
        System.out.println(locations.get(index).getLocation());

        prompt.clear();
        prompt.prompt("Location scanned successfully", "Continue");
        //TODO: UI: Show equipment at location

        //for (Equipment equip : locations.get(index).getEquipment()) {
        //    System.out.println(equip);
        //}

        //TODO Wait for button press on GPIO
        scanned = scanner.scan();
        //TODO: Check if scanned is empty

        locations.get(index).addEquipment(new Equipment(scanned));
        int eqindex = locations.get(index).getEquipment().size()-1;
        locations.get(index).getEquipment().get(eqindex).scanned();

        

        scanner.close();
    }

    public static void mainMenu(Prompt prompt) {
        prompt.clear();
        ArrayList<String> menuItems = new ArrayList<String>();
        menuItems.add("Transfer Data");
        menuItems.add("Scanning Mode");
        menuItems.add("Shutdown");
        int response = prompt.menu(menuItems);
        switch (response) {
            case 0:
                // Enter transfer mode
                break;
            case 1:
                scanMode(prompt);
                // Enter scanning mode
                break;
            case 2:
                // Shutdown
                isRunning = false;
                break;
        }
    }

    public static void main(String[] args) {

        Prompt prompt = new Prompt();

        EquipmentListing equipmentListing = new EquipmentListing("Location1");

        for (int i = 0; i < 16; i++) {
            equipmentListing.addEquipment("Sample " + i, 0);
        }
        equipmentListing.setVisible(true);


        //while (isRunning) {
        //    mainMenu(prompt);
        //}

    }

}
