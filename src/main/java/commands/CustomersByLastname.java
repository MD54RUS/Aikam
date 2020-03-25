package commands;

import java.sql.SQLException;

// Фамилия — поиск покупателей с этой фамилией
public class CustomersByLastname extends CommandExecutor {

    private final String SQL_QUERY =
            "SELECT \"ID\", \"NAME\", \"LASTNAME\" FROM \"CUSTOMER\" WHERE \"LASTNAME\"=?";

    public CustomersByLastname(String lastname) throws SQLException {
        statement = connection.prepareStatement(SQL_QUERY);
        statement.setString(1, lastname);
    }
}
