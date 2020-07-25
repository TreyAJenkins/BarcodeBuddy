import java.util.ArrayList;

public class Equipment {

    public static final int DELETED = -1;
    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int YELLOW = 2;
    public static final int BLUE = 3;
    public static final int FLASHING_ORANGE = 4;

    public static final int SESSION_DUPLICATE = FLASHING_ORANGE;
    public static final int DIFFERENT_LOCATION = YELLOW;
    public static final int NOT_IN_FILE = BLUE;
    public static final int EXISTED_IN_FILE = GREEN;
    public static final int NOT_SCANNED = RED;

    private String equipment;
    private String lastScanDate = "";
    private String serialNumber = "";
    private String manufacturer = "";
    private String modelNumber = "";
    private String description = "";
    private boolean scanned = false;
    private int type = NOT_SCANNED;
    private int lastType = DELETED;

    public Equipment(String equipment) {
        this.equipment = equipment;
    }
    public Equipment(String equipment, int type) {
        this.equipment = equipment;
        this.type = type;
    }

    public void addExtra(String lastScanDate, String serialNumber, String manufacturer, String modelNumber, String description) {
        this.lastScanDate = lastScanDate;
        this.serialNumber = serialNumber;
        this.manufacturer = manufacturer;
        this.modelNumber = modelNumber;
        this.description = description;
    }


    public String getLastScanDate() {
        return lastScanDate;
    }

    public void setLastScanDate(String lastScanDate) {
        this.lastScanDate = lastScanDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public String getDescription() {
        return description;
    }

    public void scanned(boolean value) {
        scanned = value;
    }

    public void scanned() {
        scanned = true;
    }

    public boolean isScanned() {
        return scanned;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.lastType = this.type;
        this.type = type;
    }

    public void revert() {
        int tmp = type;
        this.type = lastType;
        this.lastType = tmp;
    }

    @Override
    public String toString() {
        return equipment;
    }

    public static Equipment findElementByName(ArrayList<Equipment> equipments, String equipment) {
        for (Equipment equip : equipments) {
            if (equip.toString().equals(equipment)) {
                return equip;
            }
        }
        return null;
    }
}
