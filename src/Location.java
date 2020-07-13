import java.util.ArrayList;

public class Location {

    private String location;
    private ArrayList<Equipment> equipment;


    public Location(String location) {
        this.location = location;
        this.equipment = new ArrayList<>();
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public String getLocation() {
        return location;
    }

    public void addEquipment(Equipment equip) {
        equipment.add(equip);
    }

}
