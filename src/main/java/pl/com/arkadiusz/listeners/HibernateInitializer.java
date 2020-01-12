package pl.com.arkadiusz.listeners;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import pl.com.arkadiusz.model.Skill;
import pl.com.arkadiusz.model.Source;
import pl.com.arkadiusz.model.User;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class HibernateInitializer implements ServletContextListener {
    public static final String sFactory ="sFactory";
    private static final Logger logger = Logger.getGlobal();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Configuration configuration = new Configuration();
            Properties hbnProperties = new Properties();
            hbnProperties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            hbnProperties.put(Environment.URL, "jdbc:mysql://localhost:3306/skills_collector?useSSL=false&serverTimezone=UTC");
            hbnProperties.put(Environment.USER, "root");
            hbnProperties.put(Environment.PASS, "Demolka777$");
            hbnProperties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            hbnProperties.put(Environment.SHOW_SQL,"true");
            hbnProperties.put(Environment.FORMAT_SQL,"true");
            hbnProperties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            hbnProperties.put(Environment.HBM2DDL_AUTO, "update");
            configuration.setProperties(hbnProperties);
            configuration.addAnnotatedClass(User.class);
           configuration.addAnnotatedClass(Source.class);
           configuration.addAnnotatedClass(Skill.class);

            ServiceRegistry serviceRegistry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sce.getServletContext().setAttribute(sFactory,sessionFactory);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Błąd konfiguracji Hibernate!", e);
        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        SessionFactory sessionFactory = (SessionFactory) sce.getServletContext().getAttribute(sFactory);
        sessionFactory.close();
    }
}

