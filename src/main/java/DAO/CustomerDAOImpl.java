package DAO;

import JDBC.ConfigJDBC;
import entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl extends ConfigJDBC implements CustomerDAO {

  Connection connection = getConnection();

  @Override
  public List<Customer> getCustomerBySurname(String surname) throws SQLException {
    List<Customer> customerList = new ArrayList<>();
    String sql = "SELECT ID, NAME, SURNAME FROM CUSTOMER WHERE SURNAME=?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, surname);

      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("ID"));
        customer.setName(resultSet.getString("NAME"));
        customer.setSurname(resultSet.getString("SURNAME"));
        customerList.add(customer);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
    return customerList;
  }

  @Override
  public Customer getCustomerById(long id) throws SQLException {
    Customer customer = new Customer();
    String sql = "SELECT ID, NAME, SURNAME FROM CUSTOMER WHERE ID=?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setLong(1, id);

      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        customer.setId(resultSet.getLong("ID"));
        customer.setName(resultSet.getString("NAME"));
        customer.setSurname(resultSet.getString("SURNAME"));
      }
    }

    return customer;
  }

  @Override
  public List<Customer> getAll() throws SQLException {
    List<Customer> customerList = new ArrayList<>();
    String sql = "SELECT ID, NAME, SURNAME FROM CUSTOMER";

    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("ID"));
        customer.setName(resultSet.getString("NAME"));
        customer.setSurname(resultSet.getString("SURNAME"));
        customerList.add(customer);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
    return customerList;
  }
}
