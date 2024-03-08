package com.CleanCode.Student.Persistence.Database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDatabase {

    private static volatile ConnectionDatabase instance;
    private Connection connection;
    private static final String NAME_DATABASE = "students";
    private static final String PATH_RELATIVE_PROJECT = System.getProperty("user.dir");
    private static final String ROUTE_FILE = PATH_RELATIVE_PROJECT + "/src/main/resources/Database/" + NAME_DATABASE;


    private ConnectionDatabase() {
        if (connection == null) {
            try {
                Class.forName("org.h2.Driver");
                String url = getAddressConnector();
                connection = DriverManager.getConnection(url, "root", "root");
                System.out.println("Connection established successfully");


            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error: " + e.getMessage());

            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static synchronized ConnectionDatabase getInstance() {
        if (instance == null) {
            instance = new ConnectionDatabase();
        }
        return instance;
    }


    private String getAddressConnector() {
        return "jdbc:h2:file:" + ROUTE_FILE;
    }

}
