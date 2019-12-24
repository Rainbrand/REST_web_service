import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

/*    @NotNull used instead of @Column(nullable = false) because of the validation:
    @NotNull triggers BeanValidation that validates data before executing SQL query*/
    @OneToOne
    @JoinColumn(name = "player_from", nullable = false)
    private Player playerFrom;

    @OneToOne
    @JoinColumn(name = "player_to", nullable = false)
    private Player playerTo;

    @Column(name = "message_text")
    @NotNull
    private String messageText;

    public Message(){};

    public Message(Player playerFrom, Player playerTo, String messageText) {
        this.playerFrom = playerFrom;
        this.playerTo = playerTo;
        this.messageText = messageText;
    }

    public int getId() {
        return id;
    }
    public Player getPlayerFrom() {
        return playerFrom;
    }
    public void setPlayerFrom(Player playerFrom) {
        this.playerFrom = playerFrom;
    }
    public Player getPlayerTo() {
        return playerTo;
    }
    public void setPlayerTo(Player playerTo) {
        this.playerTo = playerTo;
    }
    public String getMessageText() {
        return messageText;
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

/*    @Override
    public String toString() {
        return "id : " + id + ",\n\tfrom : " + playerFrom.getName() + ",\n\tto : " + playerTo.getName() + "\n";
    }*/
}
