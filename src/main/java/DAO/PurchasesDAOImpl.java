package DAO;

import JDBC.ConfigJDBC;
import entity.Goods;
import entity.Purchase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchasesDAOImpl extends ConfigJDBC implements PurchasesDAO {

    Connection connection = getConnection();

    @Override
    public List<Purchase> getPurchasesByGoods(Goods goods) {
        return null;
    }

    @Override
    public List<Purchase> getPurchasesBetweenDate(Date date1, Date date2) {
        return null;
    }


    @Override
    public List<Purchase> getAll() throws SQLException {
        List<Purchase> purchaseList = new ArrayList<>();
        String sql = "SELECT ID, CUSTOMER_ID, GOODS_ID FROM PURCHASES";

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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return purchaseList;

    }
}
