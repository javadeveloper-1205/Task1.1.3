package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sqlCreateTble = "CREATE TABLE IF NOT EXISTS main.table_User (" +
                "'id' INTEGER PRIMARY KEY," +
                " 'name' CHAR(50) NOT NULL," +
                " lastName char(58) NOT NULL, 'age' int NOT NULL)";
        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            statement.executeUpdate(sqlCreateTble);
            //"CREATE TABLE IF NOT EXISTS main.tableUser"

            //"('id' integer, 'name' text, 'lastName' text, age integer)");
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                Util.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Connection connect = null;
        try {
            connect = Util.getConnection();
            Statement statement = connect.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS main.table_User");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = null;
        String sqlSaveUser = "INSERT into main.table_User (name, lastName, age) VALUES (?, ?, ?)";
        try {
            connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSaveUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            //.executeUpdate();
            //connection.createStatement().executeUpdate("INSERT into main.table_User (name, lastName, age)" +
            //      "VALUES ('" + name + "', " + "'" + lastName + "', " + age + ")");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeUserById(long id) {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            connection.createStatement().executeUpdate("DELETE FROM main.table_User WHERE id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        Statement statement = null;
        Connection connection = null;
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM main.table_User";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            connection.createStatement().executeUpdate("DELETE FROM main.table_User");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}


