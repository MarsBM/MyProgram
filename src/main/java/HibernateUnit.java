import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Мар'ян on 05.05.2016.
 */
public class HibernateUnit {
    private static SessionFactory sessionFactory = null;
    static {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
