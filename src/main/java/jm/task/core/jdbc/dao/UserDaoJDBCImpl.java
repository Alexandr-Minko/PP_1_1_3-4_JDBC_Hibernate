package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    private Statement statement;
    private String sqlCommand;
    public UserDaoJDBCImpl() {
        try {
            connection = Util.getDbConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Подключение к БД: Ошибка");
        }
    }

    public void createUsersTable() {
        sqlCommand = "CREATE TABLE user (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), lastName VARCHAR(100), age TINYINT)";
        try {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Создание таблицы: Ошибка");
        }
    }

    public void dropUsersTable() {
        sqlCommand = "DROP TABLE user";
        try {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Удаление таблицы: Ошибка");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        sqlCommand = "INSERT INTO user (name, lastName, age) Values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User c именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Добавление строки: Ошибка");
        }
    }

    public void removeUserById(long id) {
        sqlCommand = "DELETE FROM user WHERE id =" + id;
        try {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Удаление строки по ID: Ошибка");
        }
    }

    public List<User> getAllUsers() {
        sqlCommand = "SELECT * FROM user";
        ArrayList<User> listUsers = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                listUsers.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Получить всех user-ов: Ошибка");
        }
        return listUsers;
    }

    public void cleanUsersTable() {
        dropUsersTable();
        createUsersTable();
    }
}
