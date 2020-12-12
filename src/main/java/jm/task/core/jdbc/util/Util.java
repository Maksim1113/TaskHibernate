package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private final  String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/jdbc_users";

    private final String USER_NAME = "root";
    private final String PASSWORD = "root13";

    private Connection connection = null;

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
