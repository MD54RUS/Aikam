package commands;

import JDBC.DatabaseConnection;
import entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class CommandExecutor {
    Connection connection = DatabaseConnection.getInstance().getConnection();
    PreparedStatement statement;

    protected CommandExecutor() throws SQLException {
    }

    public List<Customer> execute() throws SQLException {
        ResultSet resultSet = this.statement.executeQuery();
        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setName(resultSet.getString("NAME"));
            customer.setLastName(resultSet.getString("LASTNAME"));
            customerList.add(customer);
        }
        return customerList;
    }
}
