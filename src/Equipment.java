public class Equipment {

    private String equipment;
    private boolean scanned = false;

    public Equipment(String equipment) {
        this.equipment = equipment;
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

}
