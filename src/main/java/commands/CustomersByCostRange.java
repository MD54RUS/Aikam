package commands;

import java.sql.SQLException;

//Минимальная и максимальная стоимость всех покупок —
// поиск покупателей, у которых общая стоимость всех покупок за всё время попадает в интервал

public class CustomersByCostRange extends CommandExecutor {
    private final String SQL_QUERY =
            " SELECT \"CUSTOMER\".\"NAME\",\"LASTNAME\", totalCost FROM \"CUSTOMER\" join" +
                    " (SELECT \"CUSTOMER_ID\", sum(\"GOODS\".\"PRICE\") as totalCost FROM \"PURCHASES\",\"GOODS\" where \"GOODS\".\"ID\" = \"PURCHASES\".\"GOODS_ID\" GROUP BY \"CUSTOMER_ID\")" +
                    "AS results on \"CUSTOMER\".\"ID\" = results.\"CUSTOMER_ID\"" +
                    "where totalCost > ? AND totalCost< ?";

    public CustomersByCostRange(long min, long max) throws SQLException {
    statement = connection.prepareStatement(SQL_QUERY);
        statement.setLong(1, min);
        statement.setLong(2, max);
    }
}
