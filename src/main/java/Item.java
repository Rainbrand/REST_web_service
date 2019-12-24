import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "item_type_id")
    private ItemType itemType;

    @NotNull
    private int quality;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "owner")
    private Player owner;

    public Item(){};

    public Item(ItemType itemType, int quality, Player owner) {
        this.itemType = itemType;
        this.quality = quality;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
