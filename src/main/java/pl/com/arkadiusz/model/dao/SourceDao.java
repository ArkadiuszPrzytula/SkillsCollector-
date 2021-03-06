package pl.com.arkadiusz.model.dao;

import org.hibernate.SessionFactory;
import pl.com.arkadiusz.model.Skill;
import pl.com.arkadiusz.model.Source;
import pl.com.arkadiusz.model.User;

import java.util.List;

public class SourceDao extends BaseDao {

    public SourceDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Source get(Long id){
        return super.produceInTransaction(session -> session.get(Source.class,id));
    }
    public void save(Source source){
        super.executeInTransaction(session -> session.save(source));
    }
    public void update(Source source){
        super.executeInTransaction(session -> session.update(source));
    }

    public void delete(Source source){
        super.executeInTransaction(session -> session.delete(source));
    }

    public List<Source> allSources(){
        return super.produceInTransaction(session -> session.createQuery("select DISTINCT su FROM Source su " +
                " JOIN FETCH su.attachedSkills ", Source.class).getResultList());
    }

}
