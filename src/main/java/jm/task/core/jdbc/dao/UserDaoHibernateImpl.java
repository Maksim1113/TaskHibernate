package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        String sql = "CREATE TABLE IF NOT EXISTS  users3 " +
                "(id BIGINT not NULL AUTO_INCREMENT, " +
                " name VARCHAR(50), " +
                " last_name VARCHAR (50), " +
                " age TINYINT not NULL, " +
                " PRIMARY KEY (id))";
        try  {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            session.close();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        String sql = "DROP TABLE users3";
        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            session.close();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        User user = new User(name, lastName, age);
        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
            System.out.println("User c именем " + name + " добавлен в базу данных");
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        String sql = "DELETE FROM users3 WHERE id = :id";

        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).setParameter("id", id).executeUpdate();
            transaction.commit();
            session.close();

        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }



    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users3";

        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            users = query.list();
            transaction.commit();
            session.close();

        }catch (Exception e){
           if (transaction != null){
               transaction.rollback();
            }
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        String sql = "TRUNCATE TABLE users3";
        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            session.close();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }

    }
}
