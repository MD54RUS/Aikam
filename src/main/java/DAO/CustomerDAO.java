package DAO;

import entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {

  List<Customer> getCustomerByLastName(String surname) throws SQLException;

  Customer getCustomerById(long id) throws SQLException;

  List<Customer> getAll() throws SQLException;
}
