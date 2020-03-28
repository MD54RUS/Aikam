package commands;

import DBconnector.DatabaseConnection;
import entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class SqlCommandExecutor {
    Connection connection = DatabaseConnection.getInstance().getConnection();
    PreparedStatement statement;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected SqlCommandExecutor() throws SQLException {
    }

    public List<Customer> execute() throws SQLException {
        logger.info("Executing...");
        ResultSet resultSet = statement.executeQuery();
        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setName(resultSet.getString("NAME"));
            customer.setLastName(resultSet.getString("LASTNAME"));
            customerList.add(customer);
        }
        resultSet.close();
        return customerList;
    }
}
