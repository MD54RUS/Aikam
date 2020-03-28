package commands;

import java.sql.SQLException;

/**
 * String name, long count Наименование и количество — поиск покупателей, купивших этот товар не
 * менее, чем указанное число раз
 */
public class QueryCustomerByProduct extends SqlCommandExecutor {

  public QueryCustomerByProduct(String name, long count) throws SQLException {
    final String SQL_QUERY =
            "SELECT \"NAME\",\"LASTNAME\" FROM \"CUSTOMER\" "
                    + "join (SELECT \"CUSTOMER_ID\",\"GOODS_ID\" FROM \"PURCHASES\" GROUP BY \"CUSTOMER_ID\", \"GOODS_ID\""
                    + "having count(*) >= ?) AS results on \"CUSTOMER\".\"ID\" = results.\"CUSTOMER_ID\" join (select \"ID\" "
                    + "from \"GOODS\" where \"NAME\" = ?) as \"temp\" on \"temp\".\"ID\" = results.\"GOODS_ID\"";
    statement = connection.prepareStatement(SQL_QUERY);
    statement.setLong(1, count);
    statement.setString(2, name);
  }
}
