import DBconnector.DatabaseConnection;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import commands.QueryCustomerByProduct;
import commands.QueryCustomersByCostRange;
import commands.QueryCustomersByLastname;
import commands.QueryPassiveCustomers;
import entity.Customer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExecutorsTest {
  private EmbeddedPostgres pg;
  private Connection connection;
  private Customer ivan = new Customer("Иван", "Иванов");
  private Customer petr = new Customer("Петр", "Петров");
  private Customer sidor = new Customer("Сидор", "Сидоров");

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
  public void testCustomerByProducts() throws SQLException {

      List<Customer> res = new QueryCustomerByProduct("milk", 1).execute();
    Assert.assertEquals("testCustomerByProducts", 2, res.size());
    Assert.assertTrue("testCustomerByProducts", res.contains(ivan));
    Assert.assertTrue("testCustomerByProducts", res.contains(sidor));
      Assert.assertFalse("testCustomerByProducts", res.contains(petr));
      res = new QueryCustomerByProduct("car", 0).execute();
      Assert.assertEquals("testCustomerByProducts", 0, res.size());
  }

  @Test
  public void testCustomerByLastname() throws SQLException {
      List<Customer> res = new QueryCustomersByLastname("milk").execute();
      Assert.assertEquals("testCustomerByLastname", 0, res.size());
      res = new QueryCustomersByLastname("Петров").execute();
      Assert.assertEquals("testCustomerByLastname", 1, res.size());
    Assert.assertTrue("testCustomerByLastname", res.contains(petr));
  }

  @Test
  public void testCustomerByCostRange() throws SQLException {
      List<Customer> res = new QueryCustomersByCostRange(0L, 100L).execute();
      Assert.assertEquals("testCustomerByCostRange", 3, res.size());
      res = new QueryCustomersByCostRange(57L, 100L).execute();
      Assert.assertEquals("testCustomerByCostRange", 1, res.size());
      res = new QueryCustomersByCostRange(56L, 100L).execute();
      Assert.assertEquals("testCustomerByCostRange", 2, res.size());
      res = new QueryCustomersByCostRange(0L, 67L).execute();
      Assert.assertEquals("testCustomerByCostRange", 2, res.size());
    Assert.assertTrue("testCustomerByCostRange", res.contains(petr));
    Assert.assertTrue("testCustomerByCostRange", res.contains(sidor));
    Assert.assertFalse("testCustomerByCostRange", res.contains(ivan));
  }

  @Test
  public void testPassiveCustomers() throws SQLException {
    List<Customer> res;
      res = new QueryPassiveCustomers(3).execute();
      Assert.assertEquals("testPassiveCustomers", 3, res.size());
      res = new QueryPassiveCustomers(4).execute();
      Assert.assertEquals("testPassiveCustomers", 3, res.size());
      res = new QueryPassiveCustomers(1).execute();
      Assert.assertEquals("testPassiveCustomers", 1, res.size());
      Assert.assertTrue("testPassiveCustomers", res.contains(petr));
      res = new QueryPassiveCustomers(2).execute();
      Assert.assertEquals("testPassiveCustomers", 2, res.size());
    Assert.assertTrue("testPassiveCustomers", res.contains(petr));
    Assert.assertTrue("testPassiveCustomers", res.contains(sidor));
  }
}
