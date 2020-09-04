package jm.task.core.jdbc.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import jm.task.core.jdbc.model.User;

import java.sql.*;

public class Util {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/new_schema?autoReconnect=true&useSSL=false";
    private static final String PASS = "root";
    private static final String USSERNAME = "root";
    Connection connection;



    public Connection  getConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(DB_URL, USSERNAME, PASS);
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);//соединениесБД
            System.out.println("Соединение с СУБД выполнено.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
