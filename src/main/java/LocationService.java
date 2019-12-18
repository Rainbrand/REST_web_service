import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LocationService {
    public Location findById(String id) {
        return HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                get(Location.class, id);
    }

    public Location add(int id, String description, String type) {
        Location location = new Location(id, description, type);
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
        session.save(location);
        transaction.commit();
        return location;
    }

    public void delete(String id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Location location  = (Location)session.load(Location.class, id);
        session.delete(location);
        session.close();
        System.out.println("Location " + id + " deleted.");
    }

    public List findAll() {
        return HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                createQuery("FROM Location")
                .list();
    }
}