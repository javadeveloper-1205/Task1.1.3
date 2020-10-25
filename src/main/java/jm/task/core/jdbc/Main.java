package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Greg", "Brown", (byte) 126);
        userService.saveUser("Mila", "Brown2", (byte) 121);
        userService.saveUser("Greg2", "Brown3", (byte) 122);
        userService.saveUser("Gre3", "Brown4", (byte) 123);
        userService.saveUser("Robin", "Brown5", (byte) 121);
        userService.getAllUsers().forEach(System.out::println);
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.getAllUsers().forEach(System.out::println);
        userService.dropUsersTable();
    }
}
