package commands;

import org.json.simple.JSONObject;

import java.sql.SQLException;

/**
 * Выбор SQL запроса в зависимости от требуемой операции.
 */
// не складывать в общий класс с перегрузкой методов - убивает расширяемость.

public class QueryExecutorSupplier {
    public static SqlCommandExecutor get(JSONObject criteria) throws SQLException {
        if (criteria.get("lastName") != null) {
            return new QueryCustomersByLastname((String) criteria.get("lastName"));
        }
        if (criteria.get("productName") != null) {
            return new QueryCustomerByProduct(
                    (String) criteria.get("productName"), (long) criteria.get("minTimes"));
        }
        if (criteria.get("minExpenses") != null) {
            return new QueryCustomersByCostRange(
                    (long) criteria.get("minExpenses"), (long) criteria.get("maxExpenses"));
        }
    if (criteria.get("badCustomers") != null) {
      return new QueryPassiveCustomers((long) criteria.get("badCustomers"));
    }
    return null;
  }
}
