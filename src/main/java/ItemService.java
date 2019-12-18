import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemService {
    private static Map<Integer, Item> items = new HashMap<>();
    private static ItemTypeService itemTypeService;

    public Item findById(int id) {
        return (Item) items.get(itemTypeService.findById(id));
    }

    public Item add(int id, ItemType itemType, int quality, int owner) {
        Item item = new Item(id, itemType, quality, owner);
        items.put(id, item);
        return item;
    }

    public Item update(int id, ItemType itemType, int quality, int owner) {
        Item item = (Item) items.get(itemType);
        if (id != 0){
            item.setId(id);
        }
        if (itemType != null) {
            item.setItemType(itemType);
        }
        if (quality != 0) {
            item.setQuality(quality);
        }
        if (owner != 0) {
            item.setOwner(owner);
        }
        items.put(id, item);
        return item;
    }

    public void delete(int id) {
        items.remove(id);
    }

    public List findAll() {
        return new ArrayList<>(items.values());
    }
}
