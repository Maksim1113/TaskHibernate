package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS  users " +
                "(id BIGINT not NULL AUTO_INCREMENT, " +
                " name VARCHAR(50), " +
                " last_name VARCHAR (50), " +
                " age SMALLINT not NULL, " +
                " PRIMARY KEY (id))";

        Util util = new Util();
        try (Statement stm = util.getConnection().createStatement()){
            stm.executeUpdate(sql);
        }catch (SQLException e){
            e.getStackTrace();
        }finally {
            try {
                util.getConnection().close();
            }catch (SQLException e){
                e.getMessage();
            }
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE users";
        Util util = new Util();
        try (Statement stm = util.getConnection().createStatement()) {
            stm.executeUpdate(sql);
        }catch (SQLException e){
            e.getMessage();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, last_name, age) VALUES(?, ?, ?)";
        Util util = new Util();
        try (PreparedStatement pst = util.getConnection().prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setByte(3, age);
            pst.executeUpdate();
            System.out.println("User c именем " + name + " добавлен в базу данных");
        }catch (SQLException e){
            e.getMessage();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        Util util = new Util();
        try (PreparedStatement pst = util.getConnection().prepareStatement(sql)){
            pst.setLong(1, id);
            pst.executeUpdate();
        }catch (SQLException e){
            e.getMessage();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        Util util = new Util();
        try(Statement stm = util.getConnection().createStatement()) {
            ResultSet resultSet = stm.executeQuery(sql);

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        }catch (SQLException e){
            e.getMessage();
        }
        System.out.println(users);
        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        Util util = new Util();
        try(Statement stm = util.getConnection().createStatement()) {
            stm.executeUpdate(sql);
        }catch (SQLException e){
            e.getMessage();
        }

    }
}
