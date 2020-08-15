package com.campus.exchange.util;

import com.github.fluent.hibernate.cfg.scanner.EntityScanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sun.corba.EncapsInputStreamFactory;

import java.util.Properties;

@Component
public class HibernateUtil {

    private SessionFactory sessionFactory; // ->Singleton
    private Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

    public SessionFactory getSessionFactory(){
        if(sessionFactory == null) {
            // construct session factory
            try{
                String[] modelPackages = {"com.campus.exchange.model"};
                String dbDriver = System.getProperty("database.driver");  // "org.postgresql.Driver"
                String dbDialect = System.getProperty("database.dialect"); //"org.hibernate.dialect.PostgreSQL9Dialect"
                String dbUrl = System.getProperty("database.url"); // repositoryJdbc:postgresql://localhost:5430/projectdb";
                String dbUser = System.getProperty("database.user"); // "alex";
                String dbPassword = System.getProperty("database.password"); // "password";

                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, dbDriver);
                settings.put(Environment.DIALECT, dbDialect);
                settings.put(Environment.URL, dbUrl);
                settings.put(Environment.USER, dbUser);
                settings.put(Environment.PASS, dbPassword);
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "validate");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                configuration.setProperties(settings);
                EntityScanner.scanPackages(modelPackages).addTo(configuration);

                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                ServiceRegistry serviceRegistry = registryBuilder.applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
            catch (Exception e){
                logger.error(e.getMessage(),e);
            }
        }
        return sessionFactory;
    }

//    // The main class is used for testing, but not needed anymore.
//    public static void main(String[] args){
//        SessionFactory sf = HibernateUtil.getSessionFactory();
//        logger.debug("Success generate sf" + sf.hashCode());
//        Session s = sf.openSession();
//        s.close();
//    }
}
