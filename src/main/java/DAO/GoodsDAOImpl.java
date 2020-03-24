package DAO;

import JDBC.ConfigJDBC;
import entity.Goods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                goods.setPrice(resultSet.getFloat("PRICE"));
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
}
