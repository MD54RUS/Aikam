package commands;

import java.sql.SQLException;

/**
 * long count Число пассивных покупателей — поиск покупателей, купивших меньше всего товаров.
 * Возвращается не более, чем указанное число покупателей.
 */
public class QueryPassiveCustomers extends SqlCommandExecutor {

    public QueryPassiveCustomers(long count) throws SQLException {
        final String SQL_QUERY = "SELECT \"NAME\",\"LASTNAME\" FROM \"CUSTOMER\" "
                + "join (SELECT \"CUSTOMER_ID\" FROM \"PURCHASES\" GROUP BY \"CUSTOMER_ID\""
                + "ORDER by COUNT(*) LIMIT ?) AS results on \"CUSTOMER\".\"ID\" = results.\"CUSTOMER_ID\"";
        statement = connection.prepareStatement(SQL_QUERY);
        statement.setLong(1, count);
    }
}
