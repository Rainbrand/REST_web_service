import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageService {
    private Map<Integer, Message> messages = new HashMap<>();

    public Message findById(int id) {
        return (Message) messages.get(id);
    }

    public Message add(int id, int playerFrom, int playerTo, String messageText) {
        Message message = new Message(id, playerFrom, playerTo, messageText);
        messages.put(id, message);
        return message;
    }

    public Message update(int id, int playerFrom, int playerTo, String messageText) {
        Message message = (Message) messages.get(id);
        if (id != 0) {
            message.setId(id);
        }
        if (playerFrom != 0) {
            message.setPlayerFrom(playerFrom);
        }
        if (playerTo != 0) {
            message.setPlayerTo(playerTo);
        }
        if (messageText != null) {
            message.setMessageText(messageText);
            messages.put(id, message);
        }
        return message;
    }

    public void delete ( int id){
        messages.remove(id);
    }

    public List findAll () {
        return new ArrayList<>(messages.values());
    }
}
