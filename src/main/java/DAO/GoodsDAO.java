package DAO;

import entity.Purchases;

import java.util.List;

public interface GoodsDAO {
    List<Purchases> getAll();
}
