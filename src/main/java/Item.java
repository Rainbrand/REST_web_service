public class Item {
    private int id;
    private ItemType itemType;
    private int quality;
    private int owner;  // owner - player.id

    public Item(int id, ItemType itemType, int quality, int owner) {
        this.id = id;
        this.itemType = itemType;
        this.quality = quality;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
