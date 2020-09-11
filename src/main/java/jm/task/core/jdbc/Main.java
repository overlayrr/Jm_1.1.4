package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("J", "C", (byte) 6);
        userService.saveUser("A", "O", (byte) 9);
        userService.saveUser("V", "O", (byte) 6);
        userService.saveUser("A", "L", (byte) 9);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}