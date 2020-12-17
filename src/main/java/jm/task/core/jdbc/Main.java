package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();

        userService.saveUser("Nick", "Bor", (byte)45);
        userService.saveUser("Rock", "Von", (byte)33);
        userService.saveUser("Tim", "Lik", (byte)45);
        userService.saveUser("Pop", "Tok", (byte)40);

        System.out.println(userService.getAllUsers());

        userService.removeUserById(2);

        System.out.println(userService.getAllUsers());

        //userService.cleanUsersTable();



        //userService.dropUsersTable();
    }
}
