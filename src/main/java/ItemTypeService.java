import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemTypeService {
    private static Map<Integer, ItemType> itemTypes = new HashMap<>();

    public ItemType findById(int id) {
        return (ItemType) itemTypes.get(id);
    }

    public ItemType add(int id, String name) {
        ItemType itemType = new ItemType(id, name);
        itemTypes.put(id, itemType);
        return itemType;
    }

    public ItemType update(int id, String name) {
        ItemType itemType = (ItemType) itemTypes.get(id);
        if (id != 0) {
            itemType.setId(id);
        }
        if (name != null) {
            itemType.setName(name);
        }
        itemTypes.put(id, itemType);
        return itemType;
    }

    public void delete(int id) {
        itemTypes.remove(id);
    }

    public List findAll() {
        return new ArrayList<>(itemTypes.values());
    }
}
