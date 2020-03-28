import DBconnector.DatabaseConnection;
import InputOutput.Reader;
import Service.MainLogic;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// Тестирование на Otj PG Embedded.
// Пишет в лог.

public class MainLogicTest {
  private EmbeddedPostgres pg;
  private Connection connection;
  private String resultSearch =
          "{\"type\":\"search\",\"results\":[{\"criteria\":{\"lastName\":\"Иванов\"},\"results\":[{\"name\":\"Иван\",\"lastName\":\"Иванов\"}]},{\"criteria\":{\"minTimes\":1,\"productName\":\"milk\"},\"results\":[{\"name\":\"Сидор\",\"lastName\":\"Сидоров\"},{\"name\":\"Иван\",\"lastName\":\"Иванов\"}]},{\"criteria\":{\"minExpenses\":0,\"maxExpenses\":100},\"results\":[{\"name\":\"Иван\",\"lastName\":\"Иванов\"},{\"name\":\"Петр\",\"lastName\":\"Петров\"},{\"name\":\"Сидор\",\"lastName\":\"Сидоров\"}]},{\"criteria\":{\"badCustomers\":1},\"results\":[{\"name\":\"Петр\",\"lastName\":\"Петров\"}]}]}";
    private String resultStat =
            "{\"type\":\"stat\",\"totalDays\":5234,\"customers\":[{\"name\":\"Иванов Иван\",\"purchases\":[{\"name\":\"apple\",\"expenses\":24},{\"name\":\"milk\",\"expenses\":45}],\"totalExpenses\":69},{\"name\":\"Сидоров Сидор\",\"purchases\":[{\"name\":\"apple\",\"expenses\":12}],\"totalExpenses\":12}],\"totalExpenses\":81,\"avgExpenses\":40.50}";

  public MainLogicTest() {
  }

  @Before
  public void setUp() {
    try {
      pg = EmbeddedPostgres.builder().start();
      String jdbcUrl = pg.getJdbcUrl("postgres", "postgres");
      connection = DataBuilder.exe(jdbcUrl);
    } catch (IOException | SQLException | ClassNotFoundException ignored) {
    }
    DatabaseConnection settings = mock(DatabaseConnection.class);
    setMock(settings);
    when(settings.getConnection()).thenReturn(connection);
  }

  private void setMock(DatabaseConnection mock) {
    try {
      Field instance = DatabaseConnection.class.getDeclaredField("instance");
      instance.setAccessible(true);
      instance.set(instance, mock);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @After
  public void tearDown() {
    try {
      Field instance = DatabaseConnection.class.getDeclaredField("instance");
      instance.setAccessible(true);
      instance.set(null, null);
      pg.close();
    } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testExecute() {
    TestReader reader = new TestReader();
    TestWriter writer = new TestWriter();
    MainLogic schema = new MainLogic(reader, writer);
    schema.executeSearch();
    String result = writer.getResult();
    Assert.assertEquals("MainLogic.testExecute", resultSearch, result);
  }

  @Test
  public void testExecuteStat() {
    Reader reader = new TestReader();
    TestWriter writer = new TestWriter();
    MainLogic schema = new MainLogic(reader, writer);
    schema.executeStat();
    String result = writer.getResult();
    Assert.assertEquals("MainLogic.testExecuteStat", resultStat, result);
  }
}
