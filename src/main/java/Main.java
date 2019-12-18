import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
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
    public static Connection connection = connectToDatabase("jdbc:postgresql://localhost:5432/SOA_Lab_4",
            "postgres", "my8name6is4");
    public static Statement statement;
    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection connectToDatabase(String url, String name, String password){
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/SOA_Lab_4", "postgres", "my8name6is4");

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException {


        port(4567);
        get("/", (req, res) -> "Welcome");

        post("/locations", (req, res) -> {
            Map<String, String> values = om.readValue(req.body(), new TypeReference<Map<String, String>>() {
            });
            String id = values.get("id");
            String description = values.get("description");
            String type = values.get("type");
            statement.execute("INSERT INTO locations (ID, description, type) " +
                    "VALUES ('" + id + "', '" + description + "', '" + type + "');");
            Location location = locationService.add(id, description, type);

            res.status(201);
            return om.writeValueAsString(location);
        });

        get("/locations:id", (req, res) -> {
            ResultSet result = statement.executeQuery("SELECT * from locations;");
            Location location = locationService.findById(req.params(":id"));
            if (location != null) {
                //ResultSet result = statement.executeQuery("SELECT * from locations;");
                System.out.println(result);
                return om.writeValueAsString(location);
            } else {
                res.status(404);
            }
            return om.writeValueAsString("Locations not found.");
        });

        get("/locations", (req, res) -> {
            ResultSet sqlResult = statement.executeQuery("SELECT * from locations;");
            while (sqlResult.next()){
                String id = sqlResult.getString("ID");
                String description = sqlResult.getString("description");
                String type = sqlResult.getString("type");
                System.out.println("ID: " + id + "\n" +
                        "Description: " + description + "\n" +
                        "Type: " + type + "\n");
            }
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