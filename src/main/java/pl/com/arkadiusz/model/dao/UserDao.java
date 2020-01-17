package pl.com.arkadiusz.model.dao;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import pl.com.arkadiusz.model.Skill;
import pl.com.arkadiusz.model.Source;
import pl.com.arkadiusz.model.User;

import java.util.List;

public class UserDao extends BaseDao {
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User get(Long id) {
        return super.produceInTransaction(session -> session.get(User.class, id));
    }

    public void save(User user) {
        super.executeInTransaction(session -> session.save(user));
    }

    public void update(User user) {
        super.executeInTransaction(session -> session.update(user));
    }

    public void delete(User user) {
        super.executeInTransaction(session -> session.delete(user));
    }

    public Boolean isUsernameAvailable(String username) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT count(u) FROM User u WHERE u.userName = :username", Long.class)
                        .setParameter("username", username)
                        .getSingleResult() <= 0);
    }

    public List<User> getAll() {
        return super.produceInTransaction(session -> session.createQuery("select u from User u", User.class).getResultList());
    }

    public List<User> getAllByUserName(String userName) {
        return super.produceInTransaction(session -> session.createQuery("select u from User u WHERE u.userName=:userName", User.class).setParameter("userName", userName).getResultList());
    }

    public List<User> getAllUserNameAndPassword(String userName, String password) {
        return super.produceInTransaction(session -> session.createQuery("select u from User u WHERE u.userName=:userName AND u.password=:password")
                .setParameter("userName", userName)
                .setParameter("password", password).getResultList());
    }

    public List<Skill> userSkills(User user) {
        return super.produceInTransaction(session -> session.createQuery("select s from User u " +
                "join u.knownSources ks " +
                "join ks.attachedSkills s " +
                "where u.userName=:userName", Skill.class)
                .setParameter("userName", user.getUserName())
                .getResultList());
    }

    public List<Source> userSources(User user) {
        return super.produceInTransaction(session -> session.createQuery("select DISTINCT su FROM Source su " +
                "JOIN su.users u JOIN FETCH su.attachedSkills " +
                "WHERE u.userName=:userName", Source.class).setParameter("userName", user.getUserName()).getResultList());
    }

    public List<Source> userUnknownSources(User user) {
        return super.produceInTransaction(session -> session.createQuery("select DISTINCT su FROM Source su " +
                "JOIN su.users u JOIN FETCH su.attachedSkills " +
                "WHERE NOT u.userName=:userName", Source.class).setParameter("userName", user.getUserName()).getResultList());
    }

    public void userLearnSource(String userName, Long sourceId) {
        super.executeInTransaction(session -> {
            User loggedUser = (User) session.createQuery("select u from User u where u.userName=: userName").setParameter("userName", userName).getSingleResult();
            loggedUser.getKnownSources().add(session.get(Source.class, sourceId));
        });
    }
}



