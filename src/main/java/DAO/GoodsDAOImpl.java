package DAO;

import JDBC.ConfigJDBC;
import entity.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAOImpl extends ConfigJDBC implements GoodsDAO {

  Connection connection = getConnection();

  @Override
  public List<Goods> getAll() throws SQLException {
    List<Goods> goodsList = new ArrayList<>();
    String sql = "SELECT ID, NAME, PRICE FROM GOODS";

    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        Goods goods = new Goods();
        goods.setId(resultSet.getLong("ID"));
        goods.setName(resultSet.getString("NAME"));
        goods.setPrice(resultSet.getInt("PRICE"));
        goodsList.add(goods);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
    return goodsList;
  }

  @Override
  public Goods getGodsByName(String name) throws SQLException {
    String sql = "SELECT ID, NAME, PRICE FROM GOODS WHERE NAME=?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, name);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        Goods goods = new Goods();
        goods.setId(resultSet.getLong("ID"));
        goods.setName(resultSet.getString("NAME"));
        goods.setPrice(resultSet.getInt("PRICE"));
        return goods;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
    return null;
  }

  @Override
  public Goods getGoodsById(Long id) throws SQLException {
    String sql = "SELECT ID, NAME, PRICE FROM GOODS WHERE ID=?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        Goods goods = new Goods();
        goods.setId(resultSet.getLong("ID"));
        goods.setName(resultSet.getString("NAME"));
        goods.setPrice(resultSet.getInt("PRICE"));
        return goods;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
    return null;
  }
}
