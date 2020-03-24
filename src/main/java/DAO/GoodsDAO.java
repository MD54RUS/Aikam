package DAO;

import entity.Goods;

import java.sql.SQLException;
import java.util.List;

public interface GoodsDAO {
  List<Goods> getAll() throws SQLException;
}
