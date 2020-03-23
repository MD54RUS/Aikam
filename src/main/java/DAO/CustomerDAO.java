package DAO;

import entity.Customer;

import java.util.List;

public interface CustomerDAO {

    List<Customer> getCustomerBySurname(String surname);
    Customer getCustomerById(long id);
    List<Customer> getAll();

}
