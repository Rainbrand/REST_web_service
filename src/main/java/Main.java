import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

import static spark.Spark.port;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;

public class Main {
    public static PlayerService playerService = new PlayerService();
    private static LocationService locationService = new LocationService();
    public static ObjectMapper om = new ObjectMapper();

    public static void main(String[] args) {

        port(4567);
        get("/", (req, res) -> "Welcome");

        post("/locations", (req, res) -> {
            Map<String, String> values = om.readValue(req.body(), new TypeReference<Map<String, String>>() {
            });
            String id = values.get("id");
            String description = values.get("description");
            String type = values.get("type");
            Location location = locationService.add(id, description, type);
            res.status(201);
            return om.writeValueAsString(location);
        });

        get("/locations/:id", (req, res) -> {
            Location location = locationService.findById(req.params(":id"));
            if (location != null) {
                return om.writeValueAsString(location);
            } else {
                res.status(404);
            }
            return om.writeValueAsString("Locations not found.");
        });

        get("/locations", (req, res) -> {
            List result = locationService.findAll();
            if (result.isEmpty()) {
                return om.writeValueAsString("Locations not found.");
            } else {
                return om.writeValueAsString(locationService.findAll());
            }
        });

        put("/locations/:id", (req, res) -> {
            String id = req.params(":id");
            Location location = locationService.findById(id);
            if (location != null) {
                Map<String, String> values = om.readValue(req.body(), new TypeReference<Map<String, String>>() {
                });
                String description = values.get("description");
                String type = values.get("type");
                locationService.update(id, description, type);
                return om.writeValueAsString("Location with id " + id + " is updated!");
            } else {
                res.status(404);
                return om.writeValueAsString("Location not found.");
            }
        });

        delete("/locations/:id", (req, res) -> {
            String id = req.params(":id");
            Location location = locationService.findById(id);
            if (location != null) {
                locationService.delete(id);
                return om.writeValueAsString("Location with id " + id + "is deleted");
            } else {
                res.status(404);
                return om.writeValueAsString("Location not found.");
            }
        });
    }
}