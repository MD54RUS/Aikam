package DAO;

import JDBC.DatabaseConnection;
import entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

  Connection connection;

  public CustomerDAOImpl() {
      connection = DatabaseConnection.getInstance().getConnection();
  }

  @Override
  public List<Customer> getCustomerByLastName(String lastname) {
    List<Customer> customerList = new ArrayList<>();
    String sql = "SELECT \"ID\", \"NAME\", \"LASTNAME\" FROM \"CUSTOMER\" WHERE \"LASTNAME\"=?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        //SQL-inject? (filter join select () [])
      statement.setString(1, lastname);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("ID"));
        customer.setName(resultSet.getString("NAME"));
        customer.setLastName(resultSet.getString("LASTNAME"));
        customerList.add(customer);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customerList;
  }

  @Override
  public Customer getCustomerById(long id) throws SQLException {
    Customer customer = new Customer();
    String sql = "SELECT \"ID\", \"NAME\", \"LASTNAME\" FROM \"CUSTOMER\" WHERE \"ID\" = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
          System.out.println(resultSet);
          customer.setId(resultSet.getLong("ID"));
          customer.setName(resultSet.getString("NAME"));
          customer.setLastName(resultSet.getString("LASTNAME"));
      }
    }

    return customer;
  }

  @Override
  public List<Customer> getAll() {
    List<Customer> customerList = new ArrayList<>();
    String sql = "SELECT \"ID\", \"NAME\", \"LASTNAME\" FROM \"CUSTOMER\"";

    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("ID"));
        customer.setName(resultSet.getString("NAME"));
        customer.setLastName(resultSet.getString("LASTNAME"));
        customerList.add(customer);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customerList;
  }
}
