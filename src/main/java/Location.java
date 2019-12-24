import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table (name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String description;

    @NotNull
    private String type;

    public Location(){ }

    public Location(String description, String type) {
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

//    @Override
//    public String toString() {
//        return "Location [id : " + id + ",\n\tdescription : " + description + ",\n\ttype : " + type + "]\n";
//    }
}