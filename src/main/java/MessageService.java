import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MessageService {
    public Message findById(int id) {
        return HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                get(Message.class, id);
    }

    public Message add(Player playerFrom, Player playerTo, String messageText) {
        Message message = new Message(playerFrom, playerTo, messageText);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(message);
        transaction.commit();
        session.close();
        return message;
    }

    public Message update(int id, Player playerFrom, Player playerTo, String messageText) {
        Session session = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession();
        Transaction transaction = session.beginTransaction();
        Message message = session.get(Message.class, id);
        if (playerFrom != null) {
            message.setPlayerFrom(playerFrom);
        }
        if (playerTo != null) {
            message.setPlayerTo(playerTo);
        }
        if (messageText != null) {
            message.setMessageText(messageText);
        }
        session.update(message);
        transaction.commit();
        session.close();
        return message;
    }

    public void delete (int id){
        Session session = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession();
        Transaction transaction = session.beginTransaction();
        Message message  = (Message) session.load(Message.class, id);
        session.delete(message);
        session.flush();
        transaction.commit();
        session.close();
    }

    public List findAll () {
        List messages = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                createQuery("FROM Message")
                .list();
        return messages;
    }
}
