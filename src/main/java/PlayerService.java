import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerService {
    private static Map<Integer, Player> players = new HashMap<>();

    public Player findById(int id) {
        return (Player) players.get(id);
    }

    public Player add(int id, String name, String playerClass, String email, int level, String location) {
        Player player = new Player(id, name, playerClass, email, level, location);
        players.put(id, player);
        return player;
    }

    public Player update(int id, String name, String playerClass, String email, int level, String location) {
        Player player = (Player) players.get(id);
        if (id != 0) {
            player.setId(id);
        }
        if (name != null) {
            player.setName(name);
        }
        if (playerClass != null) {
            player.setPlayerClass(playerClass);
        }
        if (email != null) {
            player.setEmail(email);
        }
        if (level != 0) {
            player.setLevel(level);
        }
        if (location != null) {
            player.setLocation(location);
        }
        players.put(id, player);
        return player;
    }

    public void delete(int id) {
        players.remove(id);
    }

    public List findAll() {
        return new ArrayList<>(players.values());
    }
}
