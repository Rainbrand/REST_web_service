import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationService {
    private static Map<String, Location> locations = new HashMap<>();

    public Location findById(String id) {
        return (Location) locations.get(id);
    }

    public Location add(String id, String description, String type) {
        Location location = new Location(id, description, type);
        locations.put(id, location);
        return location;
    }

    public Location update(String id, String description, String type) {
        Location location = (Location) locations.get(id);
        if (id != null) {
            location.setId(id);
        }
        if (description != null) {
            location.setDescription(description);
        }
        if (type != null) {
            location.setType(type);
        }
        locations.put(id, location);
        return location;
    }

    public void delete(String id) {
        locations.remove(id);
    }

    public List findAll() {
        return new ArrayList<>(locations.values());
    }
}