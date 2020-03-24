package DAO;

import JDBC.DatabaseConnection;
import entity.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAOImpl implements GoodsDAO {

  public GoodsDAOImpl() throws SQLException {
    connection = DatabaseConnection.getInstance().getConnection();
  }

  Connection connection;

  @Override
  public List<Goods> getAll() {
    List<Goods> goodsList = new ArrayList<>();
    String sql = "SELECT \"ID\", \"NAME\", \"PRICE\" FROM \"GOODS\"";

    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        Goods goods = new Goods();
        goods.setId(resultSet.getLong("ID"));
        goods.setName(resultSet.getString("NAME"));
        goods.setPrice(resultSet.getInt("PRICE"));
        goodsList.add(goods);
      }
      resultSet.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return goodsList;
  }

  @Override
  public Goods getGodsByName(String name) {
    String sql = "SELECT \"ID\", \"NAME\", \"PRICE\" FROM \"GOODS\" WHERE \"NAME\" = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, name);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        Goods goods = new Goods();
        goods.setId(resultSet.getLong("ID"));
        goods.setName(resultSet.getString("NAME"));
        goods.setPrice(resultSet.getInt("PRICE"));
        resultSet.close();
        return goods;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Goods getGoodsById(Long id) {
    String sql = "SELECT ID, NAME, PRICE FROM GOODS WHERE ID=?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        Goods goods = new Goods();
        goods.setId(resultSet.getLong("ID"));
        goods.setName(resultSet.getString("NAME"));
        goods.setPrice(resultSet.getInt("PRICE"));
        resultSet.close();
        return goods;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
