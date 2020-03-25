package commands;

import java.sql.SQLException;

// Название товара и число раз — поиск покупателей, купивших этот товар не менее, чем указанное
// число раз
public class CustomerByProduct extends CommandExecutor {
  private final String SQL_QUERY =
          "SELECT \"NAME\",\"LASTNAME\" FROM \"CUSTOMER\" "
                  + "join (SELECT \"CUSTOMER_ID\",\"GOODS_ID\" FROM \"PURCHASES\" GROUP BY \"CUSTOMER_ID\", \"GOODS_ID\""
                  + "having count(*) > ?) AS results on \"CUSTOMER\".\"ID\" = results.\"CUSTOMER_ID\" join (select \"ID\" "
                  + "from \"GOODS\" where \"NAME\" = ?) as \"temp\" on \"temp\".\"ID\" = results.\"GOODS_ID\"";

  public CustomerByProduct(String name, long count) throws SQLException {
    statement = connection.prepareStatement(SQL_QUERY);
    statement.setLong(1, count);
    statement.setString(2, name);
  }
}
