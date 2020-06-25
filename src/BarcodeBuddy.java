import java.util.ArrayList;

public class BarcodeBuddy {

    private static ArrayList<Location> locations = new ArrayList<>();

    private static void scanMode() {
        BarcodeScanner scanner = new BarcodeScanner();
        System.out.println("Please scan location barcode");
        // TODO: Wait for button press using GPIO
        String scanned = scanner.scan();
        // TODO: Check if scanned is empty

        int index = 0;
        if (locations.contains(scanned)) {
            index = locations.indexOf(scanned);
        } else {
            locations.add(new Location(scanned));
            index = locations.size()-1;
        }

        //TODO: UI: Confirm location scanned, button can be released
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

    public static void main(String[] args) {
        scanMode();
    }

}
