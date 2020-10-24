package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/grygoriyzhurylo/IdeaProjects/JM_PredProject/Task_1.1.3/test.db");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something get wrong" + e.getMessage());
        }
        return connection;
    }
}
