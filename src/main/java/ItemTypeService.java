import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ItemTypeService {
    private static ObjectMapper om = new ObjectMapper();

    public ItemType findById(int id) {
        return HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                get(ItemType.class, id);
    }

    public ItemType add(String name) {
        ItemType itemType = new ItemType(name);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(itemType);
        transaction.commit();
        session.close();
        return itemType;
    }

    public ItemType update(int id, String name) {
        Session session = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession();
        Transaction transaction = session.beginTransaction();
        ItemType itemType = session.get(ItemType.class, id);
        if (name != null) {
            itemType.setName(name);
        }
        session.update(itemType);
        transaction.commit();
        session.close();
        return itemType;
    }

    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession();
        Transaction transaction = session.beginTransaction();
        ItemType itemType = (ItemType) session.load(ItemType.class, id);
        session.delete(itemType);
        session.flush();
        transaction.commit();
        session.close();
    }

    public List findAll() {
        List itemTypes = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                createQuery("FROM ItemType")
                .list();
        return itemTypes;
    }
}
