package DAO;

import entity.Goods;
import entity.Purchases;

import java.util.Date;
import java.util.List;

public interface PurchasesDAO {
    List<Purchases> getPurchasesByGoods(Goods goods);
    List<Purchases> getPurchasesByDate(Date date);
    List<Purchases> getAll();
}
