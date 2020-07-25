import com.sun.xml.internal.ws.util.InjectionPlan;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BarcodeBuddy {

    private static ArrayList<Location> locations = new ArrayList<>();
    private static boolean isRunning = true;

    public static ArrayList<Equipment> sessionScans = new ArrayList<>();

    public static void mainMenu() {
        Prompt prompt = new Prompt();
        prompt.clear();
        ArrayList<String> menuItems = new ArrayList<String>();
        menuItems.add("Transfer Data");
        menuItems.add("Scanning Mode");
        menuItems.add("Shutdown");
        prompt.prompt("Main MENU!!", "Done");
        int response = prompt.menu(menuItems);
        System.out.println("Response: " + response);
        switch (response) {
            case 0:
                // Enter transfer mode
                break;
            case 1:
                ScanMode scanMode = new ScanMode(locations);
                scanMode.entrypoint();
                // Enter scanning mode
                break;
            case 2:
                // Shutdown
                isRunning = false;
                break;
        }
    }

    public static void main(String[] args) {

        Ingest ingest = new Ingest(new File("data.csv"));

        try {
            locations = ingest.parse();
        } catch (IOException e) {
            Prompt prompt = new Prompt();
            prompt.clear();
            prompt.prompt("Error loading data.csv", "Continue");
            prompt.clear();
        }


        /*Location location = new Location("Test Location");
        equipmentListing.setLocation(location);

        for (int i = 0; i < 16; i++) {
            location.addEquipment(new Equipment("Sample " + i, i % 4));
        }
        */

        /*Ingest ingest = new Ingest(new File("data.csv"));
        try {
            locations = ingest.parse();
            System.out.println(locations);

            for (Location loc : locations) {
                System.out.println(loc);
                for (Equipment equip : loc.getEquipment()) {
                    System.out.println("\t" + equip);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        while (isRunning) mainMenu();


    }

}
