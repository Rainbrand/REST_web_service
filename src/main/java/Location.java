import javax.persistence.*;

@Entity
@Table (name = "players")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private String type;

    public Location(){ }

    public Location(int id, String description, String type) {
        this.id = id;
        this.description = description;
        this.type = type;
    }

    public int getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Location [id =" + id  + ", description =" + description + ", type =" + type + "]";
    }
}