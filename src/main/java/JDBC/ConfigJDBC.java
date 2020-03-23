package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ConfigJDBC {
  private static final String DB_DRIVER = "org.postgresql.Driver";
  private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/aikam";
  private static final String DB_USERNAME = "reader";
  private static final String DB_PASSWORD = "111";

  public Connection getConnection() {
    Connection connection = null;
    try {

      Class.forName(DB_DRIVER);
      connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
      System.out.println("Connection created");
    } catch (ClassNotFoundException | SQLException ex) {
      System.out.println("Connection error");
        ex.printStackTrace();

    }
    return connection;
  }
}
