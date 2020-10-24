package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            connection.setAutoCommit(false);
            connection.createStatement().executeUpdate("CREATE TABLE main.table_User IF NOT EXISTS" +
                    "(INTEGER id, name text, lastName text, INTEGER age)");
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            connection.createStatement().executeUpdate("DROP TABLE main.table_User");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection.createStatement().executeUpdate("INSERT INTO main.table_User (name, lastName, age)" +
                    "VALUES ('" + name + "', " + "'" + lastName + "', " + age + ")");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            connection.createStatement().executeUpdate("DELETE FROM main.table_User WHERE id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new LinkedList<>();
        Statement statement = null;
        String sql = "SELECT * FROM main.table_User";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            connection.createStatement().executeUpdate("TRUNCATE main.table_user IGNORE DELETE TRIGGERS DROP STORAGE");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


