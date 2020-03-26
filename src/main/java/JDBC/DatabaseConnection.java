package JDBC;

import InputOutput.SettingsParser;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

  private static DatabaseConnection instance;
  private Connection connection;
  private static final String DB_DRIVER = "org.postgresql.Driver";

  private DatabaseConnection() throws SQLException {
    try {
      Class.forName(DB_DRIVER);
      DBSettings settings = SettingsParser.getDBSettings();
      this.connection =
              DriverManager.getConnection(
                      settings.getDbUrl(), settings.getDbUsername(), settings.getDbPassword());
    } catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }
  }

  public Connection getConnection() {
    return connection;
  }

  public static DatabaseConnection getInstance() {
    try {
      if (instance == null) {
        instance = new DatabaseConnection();
      } else if (instance.getConnection().isClosed()) {
        instance = new DatabaseConnection();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return instance;
  }

  public static class DBSettings {
    private final String DB_URL;
    private final String DB_USERNAME;
    private final String DB_PASSWORD;

    public DBSettings(String url, String usr, String pass) {
      DB_URL = url;
      DB_USERNAME = usr;
      DB_PASSWORD = pass;
    }

    public String getDbUrl() {
      return DB_URL;
    }

    public String getDbUsername() {
      return DB_USERNAME;
    }

    public String getDbPassword() {
      return DB_PASSWORD;
    }
  }
}
