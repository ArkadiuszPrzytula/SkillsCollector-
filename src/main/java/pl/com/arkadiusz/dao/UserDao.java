package pl.com.arkadiusz.dao;

import org.hibernate.SessionFactory;
import pl.com.arkadiusz.model.User;

public class UserDao extends BaseDao{
    public UserDao(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public User get (Long id){
        return super.produceInTransaction(session -> session.get(User.class, id));
    }

    public void save(User user){
       super.executeInTransaction(session -> session.save(user));
    }

    public void update(User user){
        super.executeInTransaction(session -> session.update(user));
    }

    public void delete(User user){
        super.executeInTransaction(session -> session.delete(user));
    }
    public Boolean isUsernameAvailable(String username) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT count(u) FROM User u WHERE u.username = :username", Long.class)
                        .setParameter("username", username)
                        .getSingleResult() <= 0;
    }
}
