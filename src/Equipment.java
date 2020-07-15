public class Equipment {

    public static final int DELETED = -1;
    public static final int NOT_SCANNED = 0;
    public static final int GREEN = 1;
    public static final int YELLOW = 2;
    public static final int FLASHING_BLUE = 3;


    private String equipment;
    private boolean scanned = false;
    private int type = NOT_SCANNED;

    public Equipment(String equipment) {
        this.equipment = equipment;
    }
    public Equipment(String equipment, int type) {
        this.equipment = equipment;
        this.type = type;
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
        this.type = type;
    }

    @Override
    public String toString() {
        return equipment;
    }
}
