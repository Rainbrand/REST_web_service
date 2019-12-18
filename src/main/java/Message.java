public class Message {
    private int id;
    private int playerFrom; // id of player
    private int playerTo; // id of player
    private String messageText;

    public Message(int id, int playerFrom, int playerTo, String messageText) {
        this.id = id;
        this.playerFrom = playerFrom;
        this.playerTo = playerTo;
        this.messageText = messageText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerFrom() {
        return playerFrom;
    }

    public void setPlayerFrom(int playerFrom) {
        this.playerFrom = playerFrom;
    }

    public int getPlayerTo() {
        return playerTo;
    }

    public void setPlayerTo(int playerTo) {
        this.playerTo = playerTo;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
