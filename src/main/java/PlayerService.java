import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlayerService {
    private static ObjectMapper om = new ObjectMapper();

    public Player findById(int id) {
        return HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                get(Player.class, id);
    }

    public Player add(String name, String playerClass, String email, int level, Location location) {
        Player player = new Player(name, playerClass, email, level, location);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(player);
        transaction.commit();
        session.close();
        return player;
    }

    public Player update(int id, String name, String playerClass, String email, int level, Location location) {
        Session session = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession();
        Transaction transaction = session.beginTransaction();
        Player player = session.get(Player.class, id);
        if (name != null) {
            player.setName(name);
        }
        if (playerClass != null) {
            player.setPlayerClass(playerClass);
        }
        if (email != null) {
            player.setEmail(email);
        }
        if (level != 0) {
            player.setLevel(level);
        }
        if (location != null) {
            player.setLocation(location);
        }
        session.update(player);
        transaction.commit();
        session.close();
        return player;
    }

    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession();
        Transaction transaction = session.beginTransaction();
        Player player  = (Player)session.load(Player.class, id);
        session.delete(player);
        session.flush();
        transaction.commit();
        session.close();
    }

    public List findAll() throws JsonProcessingException {
        List players = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                createQuery("FROM Player")
                .list();
        return players;
    }
}
