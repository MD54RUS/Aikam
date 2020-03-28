package InputOutput;

import DBconnector.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsParser {
  private static File file = new File("settings.ini");

  public static DatabaseConnection.DBSettings getDBSettings() throws IOException {
    Logger logger = LoggerFactory.getLogger(SettingsParser.class);
    Properties props = new Properties();
    props.load(new FileInputStream(file));

    final String DB_URL = props.getProperty("DB_URL");
    final String DB_USERNAME = props.getProperty("DB_USERNAME");
    final String DB_PASSWORD = props.getProperty("DB_PASSWORD");
    logger.debug(
            String.format(
                    "DBSettings: DB_URL = \"%s\", DB_USERNAME = \"%s\", using password = %b",
                    DB_URL, DB_USERNAME, !DB_PASSWORD.equals("")));
    if (DB_URL == null || DB_USERNAME == null || DB_PASSWORD == null) {
      throw new RuntimeException("Настройки подключения к БД не найдены в файле settings.ini");
    }

    return new DatabaseConnection.DBSettings(DB_URL, DB_USERNAME, DB_PASSWORD);
  }
}
