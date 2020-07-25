import java.util.ArrayList;

public class ScanMode {

    private boolean externalScanner = true;

    private ArrayList<Location> locations;
    private boolean isRunning = true;
    private BarcodeScanner barcodeScanner;
    private EquipmentListing equipmentListing;

    private Equipment lastScan;


    public ScanMode(ArrayList<Location> locations) {
        this.locations = locations;
        if (externalScanner) {
            barcodeScanner = new KeyboardBarcodeScanner();
        } else {
            barcodeScanner = new CameraBarcodeScanner();
        }
        equipmentListing = new EquipmentListing(externalScanner);
    }

    public void doLocation() {
        Prompt prompt = new Prompt();
        //BarcodeScanner scanner = new BarcodeScanner();
        BarcodeScanner scanner = new FakeBarcodeScanner("Room 50 Desk 1");
        prompt.prompt("Please scan a location barcode", "Open scanner");
        String scanned = scanner.scan();
        // TODO: Check if scanned is empty

        /*int index = 0;
        if (locations.contains(scanned)) {
            index = locations.indexOf(scanned);
            System.out.println("Location already in DB");
        } else {
            System.out.println("New Location");
            locations.add(new Location(scanned));
            index = locations.size()-1;
        }*/

        Location location = Location.findElementByName(locations, scanned);
        if (location == null) {
            location = new Location(scanned);
            locations.add(location);
        }
        equipmentListing.clear();
        equipmentListing.setLocation(location);

        prompt.clear();
        prompt.prompt("Location scanned!", "Continue");
        prompt.suicide();
        equipmentListing.setVisibility(true);
        scanner.close();

        waitForIt(equipmentListing);
    }

    private void handleScan(String scanned) {
        Prompt prompt = new Prompt();

        Location currLoc = equipmentListing.getCurrentLocation();
        Equipment currEquip = Equipment.findElementByName(currLoc.getEquipment(), scanned);
        if (currEquip == null) {
            currEquip = new Equipment(scanned, Equipment.NOT_IN_FILE);
            currLoc.addEquipment(currEquip);
        } else {
            currEquip.setType(Equipment.EXISTED_IN_FILE);
        }

        // TODO: ADD EXTRA DATA, DATE SCANNED

        // Check if equipment was found in another location
        for (Location loc : locations) {
            if (!(loc.getLocation().equals(currLoc.getLocation())) && Equipment.findElementByName(loc.getEquipment(), scanned) != null && Equipment.findElementByName(loc.getEquipment(), scanned).getType() != Equipment.DELETED) {
                System.out.println("Found at different location: " + loc.getLocation());
                currEquip.setType(Equipment.DIFFERENT_LOCATION);
            }
        }

        // Check if equipment was already scanned this session
        if (Equipment.findElementByName(BarcodeBuddy.sessionScans, scanned) != null && Equipment.findElementByName(BarcodeBuddy.sessionScans, scanned).getType() != Equipment.DELETED) {
            currEquip.setType(Equipment.SESSION_DUPLICATE);
        }

        BarcodeBuddy.sessionScans.add(currEquip);
        lastScan = currEquip;
        equipmentListing.setUndo(true);
        prompt.prompt("Equipment scanned!", "Continue");
        equipmentListing.setVisibility(true);

    }

    private void waitForIt(EquipmentListing equipmentListing) {
        while (isRunning) {
            switch (equipmentListing.getState()) {
                case EquipmentListing.IDLE:
                    break;
                case EquipmentListing.RETURN_MENU:
                    isRunning = false;
                    break;
                case EquipmentListing.RETURN_SCAN:
                    break;
                case EquipmentListing.RETURN_UNDO:
                    System.out.println("Reverting");
                    lastScan.revert();
                    equipmentListing.setUndo(false);
                    equipmentListing.setVisibility(true);
                    equipmentListing.setState(EquipmentListing.IDLE);
                    break;
            }

            if (externalScanner && ((KeyboardBarcodeScanner) barcodeScanner).isReady()) {
                String scanned = barcodeScanner.scan();
                System.out.println("Scanned: " + scanned);
                handleScan(scanned);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void entrypoint() {
        doLocation();
    }

}
