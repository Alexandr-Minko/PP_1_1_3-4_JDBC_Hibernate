package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String userName =        "root";
    private static String password =        "12345@@@";
    private static String connectionURL =   "jdbc:mysql://localhost:3306/db1";

    public static Connection getJDBCConnection() throws SQLException {
        return DriverManager.getConnection(connectionURL, userName, password);
    }

}
