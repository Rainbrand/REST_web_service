import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LocationService {
    private static ObjectMapper om = new ObjectMapper();

    public Location findById(int id) {
        return HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                get(Location.class, id);
    }

    public Location add(String description, String type) {
        Location location = new Location(description, type);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(location);
        transaction.commit();
        session.close();
        return location;
    }

    public Location update(int id, String description, String type) {
        Session session = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession();
        Transaction transaction = session.beginTransaction();
        Location location = session.get(Location.class, id);
        if (description != null) {
            location.setDescription(description);
        }
        if (type != null) {
            location.setType(type);
        }
        session.update(location);
        transaction.commit();
        session.close();
        return location;
    }

    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession();
        Transaction transaction = session.beginTransaction();
        Location location  = session.load(Location.class, id);
        session.delete(location);
        session.flush();
        transaction.commit();
        session.close();
    }

    public List findAll() throws JsonProcessingException {
        List locations = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                createQuery("FROM Location")
                .list();
        return locations;
    }
}