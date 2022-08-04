package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getDbConnection() throws SQLException {
        String UserName =        "root";
        String Password =        "12345@@@";
        String ConnectionURL =   "jdbc:mysql://localhost:3306/db1";

        Connection connection = DriverManager.getConnection(ConnectionURL, UserName, Password);
        return connection;
    }
}
