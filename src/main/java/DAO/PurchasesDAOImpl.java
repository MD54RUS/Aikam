package DAO;

import JDBC.ConfigJDBC;
import JDBC.DatabaseConnection;
import entity.Goods;
import entity.Purchase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchasesDAOImpl extends ConfigJDBC implements PurchasesDAO {

  Connection connection;

  public PurchasesDAOImpl() throws SQLException {
    connection = DatabaseConnection.getInstance().getConnection();
  }

  @Override
  public List<Purchase> getPurchasesByGoods(Goods goods) {
    // todo Exception
    if (goods == null) {
      return null;
    }
    List<Purchase> purchaseList = new ArrayList<>();
    String sql = "SELECT \"ID\", \"CUSTOMER_ID\", \"GOODS_ID\" FROM \"PURCHASES\" WHERE \"ID\" = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setLong(1, goods.getId());
      ResultSet resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        Purchase purchase = new Purchase();
        purchase.setId(resultSet.getLong("ID"));
        purchase.setCustomerId(resultSet.getLong("CUSTOMER_ID"));
        purchase.setGoodsId(resultSet.getLong("GOODS_ID"));
        purchase.setDate(resultSet.getDate("DATE"));
        purchaseList.add(purchase);
      }
      resultSet.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return purchaseList;
  }

  @Override
  public List<Purchase> getPurchasesBetweenDate(Date date1, Date date2) {
    return null;
  }

  @Override
  public List<Purchase> getAll() {
    List<Purchase> purchaseList = new ArrayList<>();
    String sql = "SELECT \"ID\", \"CUSTOMER_ID\", \"GOODS_ID\" FROM \"PURCHASES\"";

    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        Purchase purchase = new Purchase();
        purchase.setId(resultSet.getLong("ID"));
        purchase.setCustomerId(resultSet.getLong("CUSTOMER_ID"));
        purchase.setGoodsId(resultSet.getLong("GOODS_ID"));
        purchase.setDate(resultSet.getDate("DATE"));
        purchaseList.add(purchase);
      }
      resultSet.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return purchaseList;
  }
}
