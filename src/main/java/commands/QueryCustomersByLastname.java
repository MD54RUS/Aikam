package commands;

import java.sql.SQLException;

/**
 * String lastname
 * Фамилия — поиск покупателей с этой фамилией
 */

public class QueryCustomersByLastname extends SqlCommandExecutor {

  private final String SQL_QUERY =
          "SELECT \"ID\", \"NAME\", \"LASTNAME\" FROM \"CUSTOMER\" WHERE \"LASTNAME\"=?";

  public QueryCustomersByLastname(String lastname) throws SQLException {
    statement = connection.prepareStatement(SQL_QUERY);
    statement.setString(1, lastname);
  }
}
