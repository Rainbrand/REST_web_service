import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ItemService {
    public Item findById(int id) {
        return HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                get(Item.class, id);
    }

    public Item add(ItemType itemType, int quality, Player owner) {
        Item item = new Item(itemType, quality, owner);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(item);
        transaction.commit();
        session.close();
        return item;
    }

    public Item update(int id, ItemType itemType, int quality, Player owner) {
        Session session = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession();
        Transaction transaction = session.beginTransaction();
        Item item = session.get(Item.class, id);
        if (itemType != null) {
            item.setItemType(itemType);
        }
        item.setQuality(quality);
        if (owner != null) {
            item.setOwner(owner);
        }
        session.update(item);
        transaction.commit();
        session.close();
        return item;
    }

    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession();
        Transaction transaction = session.beginTransaction();
        Item item  = (Item) session.load(Item.class, id);
        session.delete(item);
        session.flush();
        transaction.commit();
        session.close();
    }

    public List findAll() {
        List items = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                createQuery("FROM Item")
                .list();
        return items;
    }
}
