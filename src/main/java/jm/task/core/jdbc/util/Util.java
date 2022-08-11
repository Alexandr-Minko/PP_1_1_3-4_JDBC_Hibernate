package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String userName =        "root";
    private static final String password =        "12345@@@";
    private static final String connectionURL =   "jdbc:mysql://localhost:3306/db1";

    public static Connection getJDBCConnection() throws SQLException {
        return DriverManager.getConnection(connectionURL, userName, password);
    }

    public static SessionFactory getHibernateSessionFactory() {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.url", connectionURL)
                .setProperty("hibernate.connection.username", userName)
                .setProperty("hibernate.connection.password", password)
                .setProperty("show_sql", "true")
                .setProperty("hibernate.format_sql", "true")
                .addAnnotatedClass(User.class);

        return configuration.buildSessionFactory();
    }
}