package commands;

import java.sql.SQLException;

// Число пассивных покупателей — поиск покупателей, купивших меньше всего товаров.
// Возвращается не более, чем указанное число покупателей.
public class PassiveCustomers extends CommandExecutor {

    private final String SQL_QUERY =
            "SELECT \"NAME\",\"LASTNAME\" FROM \"CUSTOMER\" "
                    + "join (SELECT \"CUSTOMER_ID\" FROM \"PURCHASES\" GROUP BY \"CUSTOMER_ID\""
                    + "ORDER by COUNT(*) LIMIT ?) AS results on \"CUSTOMER\".\"ID\" = results.\"CUSTOMER_ID\"";

    public PassiveCustomers(long count) throws SQLException {
        statement = connection.prepareStatement(SQL_QUERY);
        statement.setLong(1, count);
    }
}
