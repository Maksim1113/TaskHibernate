package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.mapping.MetadataSource;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private final  String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/jdbc_users";

    private final String USER_NAME = "root";
    private final String PASSWORD = "root13";

    private Connection connection = null;

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        try {
            Configuration config = new Configuration();
            Properties properties = new Properties();

            properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/jdbc_users");
            properties.setProperty(Environment.USER, "root");
            properties.setProperty(Environment.PASS, "root13");
            properties.setProperty(Environment.SHOW_SQL, "true");
            properties.setProperty(Environment.HBM2DDL_AUTO, "update");
            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

            config.setProperties(properties);
            config.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(config.getProperties()).build();

            sessionFactory = config.buildSessionFactory(serviceRegistry);

            //sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }




    public Util() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        }catch (ClassNotFoundException | SQLException e){
            System.out.println("Что-то пошло не так попробуйте еще раз");
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
