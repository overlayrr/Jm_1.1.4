package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Roman","Roman", (byte) 27);
        userService.saveUser("Ivan","Roman", (byte) 27);
        userService.saveUser("Igor ","Roman", (byte) 27);
        userService.saveUser("Nikita","Roman", (byte) 27);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
