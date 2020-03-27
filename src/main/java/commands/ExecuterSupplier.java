package commands;

import org.json.simple.JSONObject;

import java.sql.SQLException;
//Выбор SQL запроса в зависимости от требуемой операции.
public class ExecuterSupplier {
  public static CommandExecutor get(JSONObject criteria) throws SQLException {
    if (criteria.get("lastName") != null) {
      return new CustomersByLastname((String) criteria.get("lastName"));
    }
    if (criteria.get("productName") != null) {
      return new CustomerByProduct(
              (String) criteria.get("productName"), (long) criteria.get("minTimes"));
    }
    if (criteria.get("minExpenses") != null) {
      return new CustomersByCostRange(
              (long) criteria.get("minExpenses"), (long) criteria.get("maxExpenses"));
    }
    if (criteria.get("badCustomers") != null) {
      return new PassiveCustomers((long) criteria.get("badCustomers"));
    }
    return null;
  }
}
