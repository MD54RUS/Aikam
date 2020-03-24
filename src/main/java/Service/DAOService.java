package Service;

import DAO.CustomerDAO;
import DAO.GoodsDAO;
import DAO.PurchasesDAO;
import entity.Criteria;
import entity.Customer;
import entity.Purchase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DAOService {
  private CustomerDAO customerRepository;
  private GoodsDAO goodsRepository;
  private PurchasesDAO purchasesRepository;

  public DAOService(
      CustomerDAO customerRepository, GoodsDAO goodsRepository, PurchasesDAO purchasesRepository) {
    this.customerRepository = customerRepository;
    this.goodsRepository = goodsRepository;
    this.purchasesRepository = purchasesRepository;
  }

  public List<Customer> search(Criteria criteria) throws SQLException {

    // поиск покупателей с этой фамилией
    if (criteria.getLastName() != null) {
      return customerRepository.getCustomerByLastName(criteria.getLastName());
    }

    // поиск покупателей, купивших этот товар не менее, чем указанное число раз
    if (criteria.getProductName() != null) {
      List<Purchase> purchases =
          purchasesRepository.getPurchasesByGoods(
              goodsRepository.getGodsByName(criteria.getProductName()));
      Map<Long, Long> counted =
          purchases.stream()
              .collect(Collectors.groupingBy(Purchase::getCustomerId, Collectors.counting()));
      List<Customer> res = new ArrayList<>();
      counted.forEach(
          (id, count) -> {
            if (count >= criteria.getMinTimes()) {
              try {
                res.add(customerRepository.getCustomerById(id));
              } catch (SQLException e) {
                e.printStackTrace();
              }
            }
          });
      return res;
    }

    // поиск покупателей, у которых общая стоимость всех покупок за всё время попадает в интервал
    if (criteria.getMinExpenses() != null) {
      List<Purchase> purchases = purchasesRepository.getAll();
      Map<Long, Long> debt = new HashMap<>();
      purchases.forEach(
          purchase -> {
            Long sum = debt.get(purchase.getCustomerId());
            int price = 0;
            try {
              price = goodsRepository.getGoodsById(purchase.getGoodsId()).getPrice();
            } catch (SQLException e) {
              e.printStackTrace();
            }
            if (sum != null) {
              debt.replace(purchase.getCustomerId(), sum + price);
            } else {
              debt.put(purchase.getCustomerId(), (long) price);
            }
          });
      List<Customer> res = new ArrayList<>();
      debt.forEach(
          (id, sum) -> {
            if (sum >= criteria.getMinExpenses() && sum <= criteria.getMaxExpenses()) {
              try {
                res.add(customerRepository.getCustomerById(id));
              } catch (SQLException e) {
                e.printStackTrace();
              }
            }
          });
      return res;
    }

    // Число пассивных покупателей — поиск покупателей, купивших меньше всего товаров.
    // Возвращается не более, чем указанное число покупателей.

    if (criteria.getBadCustomers() != 0) {
      List<Purchase> purchases = purchasesRepository.getAll();
      List<Long> counted =
          purchases.stream()
              .collect(Collectors.groupingBy(Purchase::getCustomerId, Collectors.counting()))
              .entrySet()
              .stream()
              .sorted(Map.Entry.comparingByValue())
              .map(Map.Entry::getKey)
              .collect(Collectors.toList());

      List<Customer> res = new ArrayList<>();
      for (int i = 0, k = criteria.getBadCustomers(), m = counted.size() - 1; i < k; i++) {
        if (k > m) {
          break;
        }
        res.add(customerRepository.getCustomerById(counted.get(i)));
      }
      return res;
    }
    return null;
  }
}
