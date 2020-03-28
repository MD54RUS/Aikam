package commands;

import com.fasterxml.jackson.databind.JsonNode;

import java.sql.SQLException;

/**
 * Выбор SQL запроса в зависимости от требуемой операции.
 */
// Перегрузка?

public class QueryExecutorSupplier {
  public static SqlCommandExecutor get(JsonNode criteria) throws SQLException {
    if (criteria.get("lastName") != null) {
      return new QueryCustomersByLastname(criteria.get("lastName").asText());
    }
    if (criteria.get("productName") != null) {
      return new QueryCustomerByProduct(
              criteria.get("productName").asText(), criteria.get("minTimes").asLong());
    }
    if (criteria.get("minExpenses") != null) {
      return new QueryCustomersByCostRange(
              criteria.get("minExpenses").asLong(), criteria.get("maxExpenses").asLong());
    }
    if (criteria.get("badCustomers") != null) {
      return new QueryPassiveCustomers(criteria.get("badCustomers").asLong());
    }
    throw new RuntimeException("Неправильно задан критерий для поиска.");
  }
}
