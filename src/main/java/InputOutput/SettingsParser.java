package InputOutput;

import JDBC.DatabaseConnection;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

public class SettingsParser {
  private static File file = new File("settings.ini");

  public static DatabaseConnection.DBSettings getDBSettings() throws IOException {
    Wini ini = new Wini(file);
    final String DB_URL = ini.get("DataBase settings", "DB_URL");
    final String DB_USERNAME = ini.get("DataBase settings", "DB_USERNAME");
    final String DB_PASSWORD = ini.get("DataBase settings", "DB_PASSWORD");
    return new DatabaseConnection.DBSettings(DB_URL, DB_USERNAME, DB_PASSWORD);
  }
}
