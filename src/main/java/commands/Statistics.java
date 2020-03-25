package commands;

import DTO.AnswerStatisticsDTO;
import JDBC.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Statistics {

  private LocalDate startDate;
  private LocalDate endDate;
  private PreparedStatement statement;
  private Connection connection;
  private List<AnswerStatisticsDTO.CustomersDTO> result;

  private final String SQL_QUERY_CUSTOMERS =
          "SELECT p.\"CUSTOMER_ID\", CONCAT (c.\"LASTNAME\" ,' ',c.\"NAME\") As FullName "
                  + "FROM \"PURCHASES\" as p"
                  + "join \"CUSTOMER\" as c on p.\"CUSTOMER_ID\" = c.\"ID\""
                  + "where \"DATE\" between ? and ? and EXTRACT(dow FROM \"DATE\") not in (6,0)"
                  + "GROUP BY \"CUSTOMER_ID\",FullName";
  private final String SQL_QUERY_PURCHASES =
          "SELECT b.\"NAME\", COUNT(DISTINCT p)*b.\"PRICE\" as \"total\""
                  + "from \"GOODS\" as b"
                  + "join \"PURCHASES\" as p on p.\"GOODS_ID\" = b.\"ID\""
                  + " where p.\"CUSTOMER_ID\" = ? and p.\"DATE\" between ? and ? "
                  + "and EXTRACT(dow FROM p.\"DATE\") not in (6,0) "
                  + " group by b.\"NAME\",b.\"PRICE\"";

  public Statistics(LocalDate startDate, LocalDate endDate) throws SQLException {
    this.startDate = startDate;
    this.endDate = endDate;
    connection = DatabaseConnection.getInstance().getConnection();
    statement = connection.prepareStatement(SQL_QUERY_CUSTOMERS);
    statement.setString(1, startDate.plusDays(1L).toString());
    statement.setString(2, endDate.minusDays(1L).toString());
    result = new ArrayList<>();
  }

  public List<AnswerStatisticsDTO.CustomersDTO> execute() throws SQLException {
    ResultSet resultSet = statement.executeQuery();
    SortedMap<Long, String> customerList = new TreeMap<>();
    while (resultSet.next()) {
      customerList.put(resultSet.getLong("ID"), resultSet.getString("FullName"));
    }
    resultSet.close();
    List<AnswerStatisticsDTO.CustomersDTO> customersDTOList = new ArrayList<>();
    AnswerStatisticsDTO.CustomersDTO customerDTO;
    for (Long x : customerList.keySet()) {
      statement = connection.prepareStatement(SQL_QUERY_PURCHASES);
      statement.setLong(1, x);
      statement.setString(2, startDate.plusDays(1L).toString());
      statement.setString(3, endDate.minusDays(1L).toString());
      customerDTO = new AnswerStatisticsDTO.CustomersDTO(customerList.get(x));
      ResultSet resultSet1 = statement.executeQuery();
      while (resultSet1.next()) {

        AnswerStatisticsDTO.GoodsDTO purchase =
                new AnswerStatisticsDTO.GoodsDTO(
                        resultSet1.getString("NAME"), resultSet1.getInt("total"));
        customerDTO.addPurchases(purchase);
      }
      customersDTOList.add(customerDTO);
    }
    return customersDTOList;
  }
}
