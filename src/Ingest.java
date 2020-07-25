import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;

public class Ingest {

    private File dataFile;

    public Ingest(File dataFile) {
        this.dataFile = dataFile;
    }

    public ArrayList<Location> parse() throws IOException {
        ArrayList<Location> locations = new ArrayList<>();
        CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader()
                .withIgnoreHeaderCase().parse(new InputStreamReader(new FileInputStream(dataFile)));

        for (CSVRecord record : csvParser) {
            //System.out.println(record);
            Location location = Location.findElementByName(locations, record.get("Location"));
            if (location == null) {
                location = new Location(record.get("Location"));
                locations.add(location);
            }
            Equipment equipment = new Equipment(record.get("Equipment Code"));
            equipment.addExtra(record.get("Last Scan Date"), record.get("Serial Number"), record.get("Manufacturer"), record.get("Model Number"), record.get("Description"));
            location.addEquipment(equipment);
        }

        return locations;
    }

}
