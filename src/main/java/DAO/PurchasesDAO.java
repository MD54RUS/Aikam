package DAO;

import entity.Goods;
import entity.Purchase;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface PurchasesDAO {

  List<Purchase> getPurchasesByGoods(Goods goods) throws SQLException;

  List<Purchase> getPurchasesBetweenDate(Date date1, Date date2);

  List<Purchase> getAll() throws SQLException;
}
