/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecah2;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Biblio2HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        Configuration configuration
                = new Configuration().configure("bibliotecah2/hibernate.cfg.xml");
        StandardServiceRegistryBuilder serviceRegistryBuilder
                = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
        StandardServiceRegistryBuilder
                .destroy(sessionFactory.getSessionFactoryOptions().getServiceRegistry());
    }
}
