import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "item_types")
public class ItemType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    public ItemType(){};

    public ItemType(String name) {
        this.name = name;
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
}
