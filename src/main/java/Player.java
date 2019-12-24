import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table (name = "players")
public class Player{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @NotNull
    private String name;

    @Column(name = "class")
    @NotNull
    private String playerClass;

    @NotNull
    private String email;

    @NotNull
    private int level;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location")
    private Location location;

    public Player(){ }

    public Player(String name, String playerClass, String email, int level, Location location) {
        this.name = name;
        this.playerClass = playerClass;
        this.email = email;
        this.level = level;
        this.location = location;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPlayerClass() {
        return playerClass;
    }
    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

/*    @Override
    public String toString() {
        return "Player [id : " + id + ",\n\tname : " + name + ",\n\tclass : " + playerClass +
                ",\n\temail : "+ email + ",\n\tlevel : "+ level + ",\n\temail : " + location +"]\n";
    }*/
}
