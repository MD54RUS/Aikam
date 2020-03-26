package JDBC;

import InputOutput.SettingsParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

  private static DatabaseConnection instance;
  private Connection connection;
  private static final String DB_DRIVER = "org.postgresql.Driver";

  private DatabaseConnection() throws SQLException {
    Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
    try {
      Class.forName(DB_DRIVER);
      DBSettings settings = SettingsParser.getDBSettings();
      this.connection =
              DriverManager.getConnection(
                      settings.getDbUrl(), settings.getDbUsername(), settings.getDbPassword());
      logger.info("Connection to DB established");
    } catch (ClassNotFoundException e) {
      logger.error("Driver not found", e);
      throw new RuntimeException("Cant connect to the DB");
    } catch (IOException e) {
      logger.error("DB settings not found", e);
      throw new RuntimeException("Cant connect to the DB");
    }
  }

  public Connection getConnection() {
    return connection;
  }

  public static DatabaseConnection getInstance() throws SQLException {

    if (instance == null) {
      instance = new DatabaseConnection();
    } else if (instance.getConnection().isClosed()) {
      instance = new DatabaseConnection();
    }
    return instance;
  }

  //DTO для настроек коннекта к БД
  public static class DBSettings {
    private final String DB_URL;
    private final String DB_USERNAME;
    private final String DB_PASSWORD;

    public DBSettings(String url, String usr, String pass) {
      DB_URL = url;
      DB_USERNAME = usr;
      DB_PASSWORD = pass;
    }

    protected String getDbUrl() {
      return DB_URL;
    }

    protected String getDbUsername() {
      return DB_USERNAME;
    }

    protected String getDbPassword() {
      return DB_PASSWORD;
    }
  }
}
