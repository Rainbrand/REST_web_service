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
    private static PlayerService playerService = new PlayerService();
    private static LocationService locationService = new LocationService();
    private static ItemTypeService itemTypeService = new ItemTypeService();
    private static ItemService itemService = new ItemService();
    private static MessageService messageService = new MessageService();
    private static ObjectMapper om = new ObjectMapper();

    public static void main(String[] args) {
        port(4567);
        get("/", (req, res) -> "Welcome");

        post("/locations", (req, res) -> {
            Map<String, String> values = om.readValue(req.body(),
                    new TypeReference<Map<String, String>>(){});
            String description = values.get("description");
            String type = values.get("type");
            Location location = locationService.add(description, type);
            res.status(201);
            return om.writeValueAsString(location);
        });

        post("/players", (req, res) -> {
            Map<String, String> values = om.readValue(req.body(),
                    new TypeReference<Map<String, String>>(){});
            String name = values.get("name");
            String playerClass = values.get("playerClass");
            String email = values.get("email");
            String level = values.get("level");
            Location location = locationService.findById(Integer.valueOf(values.get("location")));
            Player player = playerService.add(name, playerClass, email, Integer.valueOf(level), location);
            res.status(201);
            return om.writeValueAsString(player);
        });

        post("/item_types", (req, res) -> {
            Map<String, String> values = om.readValue(req.body(),
                    new TypeReference<Map<String, String>>(){});
            String name = values.get("name");
            ItemType itemType = itemTypeService.add(name);
            res.status(201);
            return om.writeValueAsString(itemType);
        });

        post("/items", (req, res) -> {
            Map<String, String> values = om.readValue(req.body(),
                    new TypeReference<Map<String, String>>(){});
            ItemType itemType = itemTypeService.findById(Integer.valueOf(values.get("type")));
            int quality = Integer.valueOf(values.get("quality"));
            Player owner = playerService.findById(Integer.valueOf(values.get("owner")));
            Item item = itemService.add(itemType, quality, owner);
            res.status(201);
            return om.writeValueAsString(item);
        });

        post("/messages", (req, res) -> {
            Map<String, String> values = om.readValue(req.body(),
                    new TypeReference<Map<String, String>>(){});
            Player playerFrom = playerService.findById(Integer.valueOf(values.get("from")));
            Player playerTo = playerService.findById(Integer.valueOf(values.get("to")));
            String messageText = values.get("message");
            Message message = messageService.add(playerFrom, playerTo, messageText);
            res.status(201);
            return om.writeValueAsString(message);
        });

        get("/locations", (req,res) -> {
            List <Location> result = locationService.findAll();
            if (result.isEmpty()){
                return om.writeValueAsString("Locations not found.");
            }else {
                return om.writeValueAsString(result);
            }
        });

        get("/players", (req,res) -> {
            List <Player> result = playerService.findAll();
            if (result.isEmpty()){
                return om.writeValueAsString("Players not found.");
            }else {
                return om.writeValueAsString(result);
            }
        });

        get("/item_types", (req,res) -> {
            List <ItemType> result = itemTypeService.findAll();
            if (result.isEmpty()){
                return om.writeValueAsString("Item type not found.");
            }else {
                return om.writeValueAsString(result);
            }
        });

        get("/items", (req,res) -> {
            List <Item> result = itemService.findAll();
            if (result.isEmpty()){
                return om.writeValueAsString("Items not found.");
            }else {
                return om.writeValueAsString(result);
            }
        });

        get("/messages", (req,res) -> {
            List <Message> result = messageService.findAll();
            if (result.isEmpty()){
                return om.writeValueAsString("Messages not found.");
            }else {
                return om.writeValueAsString(result);
            }
        });

        get("/locations/:id", (req,res) -> {
            int id = Integer.valueOf(req.params(":id"));
            Location location = locationService.findById(id);
            if (location != null) {
                return om.writeValueAsString(location);
            }else {
                res.status(404);
                return om.writeValueAsString("Location not found.");
            }
        });

        get("/players/:id", (req,res) -> {
            int id = Integer.valueOf(req.params(":id"));
            Player player = playerService.findById(id);
            if (player != null) {
                return om.writeValueAsString(player);
            }else {
                res.status(404);
                return om.writeValueAsString("Player not found.");
            }
        });

        get("/item_types/:id", (req,res) -> {
            int id = Integer.valueOf(req.params(":id"));
            ItemType itemType = itemTypeService.findById(id);
            if (itemType != null) {
                return om.writeValueAsString(itemType);
            }else {
                res.status(404);
                return om.writeValueAsString("Item type not found.");
            }
        });

        get("/items/:id", (req,res) -> {
            int id = Integer.valueOf(req.params(":id"));
            Item item = itemService.findById(id);
            if (item != null) {
                return om.writeValueAsString(item);
            }else {
                res.status(404);
                return om.writeValueAsString("Item not found.");
            }
        });

        get("/messages/:id", (req,res) -> {
            int id = Integer.valueOf(req.params(":id"));
            Message message = messageService.findById(id);
            if (message != null){
                return om.writeValueAsString(message);
            }else {
                res.status(404);
                return om.writeValueAsString("Message not found.");
            }
        });

        put("/locations/:id", (req, res) -> {
            int id = Integer.valueOf(req.params(":id"));
            Location location = locationService.findById(id);
            if(location != null){
                Map<String, String> values = om.readValue(req.body(),
                        new TypeReference<Map<String, String>>(){});
                String description = values.get("description");
                String type = values.get("type");
                locationService.update(id, description, type);
                return om.writeValueAsString("Location with id " + id + " is updated.");
            } else {
                res.status(404);
                return om.writeValueAsString("Location not found.");
            }
        });

        put("/players/:id", (req, res) -> {
            int id = Integer.valueOf(req.params(":id"));
            Player player = playerService.findById(id);
            if(player != null){
                Map<String, String> values = om.readValue(req.body(),
                        new TypeReference<Map<String, String>>(){});
                String name = values.get("name");
                String playerClass = values.get("playerClass");
                String email = values.get("email");
                int level = Integer.valueOf(values.get("level"));
                Location location = locationService.findById(Integer.valueOf(values.get("location")));
                playerService.update(id, name, playerClass, email, level, location);
                return om.writeValueAsString("Player with id " + id + " is updated.");
            } else {
                res.status(404);
                return om.writeValueAsString("Player not found.");
            }
        });

        put("/item_types/:id", (req, res) -> {
            int id = Integer.valueOf(req.params(":id"));
            ItemType itemType = itemTypeService.findById(id);
            if(itemType != null){
                Map<String, String> values = om.readValue(req.body(),
                        new TypeReference<Map<String, String>>(){});
                String name = values.get("name");
                itemTypeService.update(id, name);
                return om.writeValueAsString("Item type with id " + id + " is updated.");
            } else {
                res.status(404);
                return om.writeValueAsString("Item type not found.");
            }
        });

        put("/items/:id", (req, res) -> {
            int id = Integer.valueOf(req.params(":id"));
            Item item = itemService.findById(id);
            if(item != null){
                Map<String, String> values = om.readValue(req.body(),
                        new TypeReference<Map<String, String>>(){});
                ItemType itemType = itemTypeService.findById(Integer.valueOf(values.get("type")));
                int quality = Integer.valueOf(values.get("quality"));
                Player owner = playerService.findById(Integer.valueOf("owner"));
                itemService.update(id, itemType, quality, owner);
                return om.writeValueAsString("Item with id " + id + " is updated.");
            } else {
                res.status(404);
                return om.writeValueAsString("Item not found.");
            }
        });

        put("/messages/:id", (req, res) -> {
            int id = Integer.valueOf(req.params(":id"));
            Message message = messageService.findById(id);
            if(message != null){
                Map<String, String> values = om.readValue(req.body(),
                        new TypeReference<Map<String, String>>(){});
                Player playerFrom = playerService.findById(Integer.valueOf("from"));
                Player playerTo = playerService.findById(Integer.valueOf("to"));
                String messageText = values.get("message");
                messageService.update(id, playerFrom, playerTo, messageText);
                res.status(201);
                return om.writeValueAsString(message);
            } else {
                res.status(404);
                return om.writeValueAsString("Message not found.");
            }
        });

        delete("/locations/:id", (req, res) ->{
            int id = Integer.valueOf(req.params(":id"));
            Location location = locationService.findById(id);
            if(location != null){
                locationService.delete(id);
                return om.writeValueAsString("Location with id " + id + " is deleted.");
            }else {
                res.status(404);
                return om.writeValueAsString("Location not found.");
            }
        });

        delete("/players/:id", (req, res) ->{
            int id = Integer.valueOf(req.params(":id"));
            Player player = playerService.findById(id);
            if(player != null){
                playerService.delete(id);
                return om.writeValueAsString("Player with id " + id + " is deleted.");
            }else {
                res.status(404);
                return om.writeValueAsString("Player not found.");
            }
        });

        delete("/item_types/:id", (req, res) ->{
            int id = Integer.valueOf(req.params(":id"));
            ItemType itemType = itemTypeService.findById(id);
            if(itemType != null){
                itemTypeService.delete(id);
                return om.writeValueAsString("Item type with id " + id + " is deleted.");
            }else {
                res.status(404);
                return om.writeValueAsString("Item type not found.");
            }
        });

        delete("/items/:id", (req, res) ->{
            int id = Integer.valueOf(req.params(":id"));
            Item item = itemService.findById(id);
            if(item != null){
                itemService.delete(id);
                return om.writeValueAsString("Item with id " + id + " is deleted.");
            }else {
                res.status(404);
                return om.writeValueAsString("Item not found.");
            }
        });

        delete("/messages/:id", (req, res) ->{
            int id = Integer.valueOf(req.params(":id"));
            Message message = messageService.findById(id);
            if(message != null){
                messageService.delete(id);
                return om.writeValueAsString("Message with id " + id + " is deleted.");
            }else {
                res.status(404);
                return om.writeValueAsString("Message not found.");
            }
        });
    }
}