import DTO.AnswerTemplate;
import InputOutput.Reader;
import InputOutput.Writer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DataBuilder {
  static final String initCustomerTable =
          "CREATE TABLE \"CUSTOMER\"\n"
                  + "("
                  + "    \"ID\" bigserial ,"
                  + "    \"NAME\" text COLLATE pg_catalog.\"default\","
                  + "    \"LASTNAME\" text COLLATE pg_catalog.\"default\","
                  + "    CONSTRAINT \"CUSTOMER_pkey\" PRIMARY KEY (\"ID\")"
                  + ")"
                  + "TABLESPACE pg_default;";
  static final String initGoodsTable =
          "CREATE TABLE \"GOODS\""
                  + "("
                  + "    \"ID\" bigserial,"
                  + "    \"NAME\" text COLLATE pg_catalog.\"default\","
                  + "    \"PRICE\" integer,\n"
                  + "    CONSTRAINT \"GOODS_pkey\" PRIMARY KEY (\"ID\")"
                  + ")"
                  + "TABLESPACE pg_default;";
  static final String initTablePurchases =
          "CREATE TABLE \"PURCHASES\""
                  + "("
                  + "    \"ID\" bigserial,"
                  + "    \"CUSTOMER_ID\" bigint NOT NULL,"
                  + "    \"GOODS_ID\" bigint NOT NULL,"
                  + "    \"DATE\" date NOT NULL,"
                  + "    CONSTRAINT \"PURCHASES_pkey\" PRIMARY KEY (\"ID\")"
                  + ")"
                  + "TABLESPACE pg_default;";
  static final String loadCustomerData =
          "insert into \"CUSTOMER\" (\"ID\", \"NAME\", \"LASTNAME\") values"
                  + "('1', 'Иван', 'Иванов'),"
                  + "('2', 'Петр', 'Петров'),"
                  + "('3', 'Сидор', 'Сидоров');";
  static final String loadGoodsData =
          "insert into \"GOODS\" (\"ID\", \"NAME\", \"PRICE\") values"
                  + "('1', 'apple', '12'),"
                  + "('2', 'milk', '45');";
  static final String loadPurchaseData =
          "insert into \"PURCHASES\" (\"ID\", \"CUSTOMER_ID\", \"GOODS_ID\", \"DATE\") values"
                  + "('1','1','1','2001-01-01'),"
                  + "('2','2','1','2002-02-02'),"
                  + "('3','1','1','2001-01-02'),"
                  + "('5','3','2','2002-02-02'),"
                  + "('6','3','1','2001-01-01'),"
                  + "('7','1','2','2001-01-01');";
  private static final String DB_DRIVER = "org.postgresql.Driver";

  static Connection exe(String jdbcUrl) throws SQLException, ClassNotFoundException {
    Class.forName(DB_DRIVER);
    Connection connection = DriverManager.getConnection(jdbcUrl, "posgres", "posgres");
    Statement statement = connection.createStatement();
    statement.execute(initCustomerTable);
    statement.execute(initGoodsTable);
    statement.execute(initTablePurchases);
    statement.execute(loadCustomerData);
    statement.execute(loadGoodsData);
    statement.execute(loadPurchaseData);
    return connection;
  }
}

class TestWriter implements Writer {
  private String result = "";

  @Override
  public void write(AnswerTemplate answer) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      result = mapper.writeValueAsString(answer);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  public String getResult() {
    return result;
  }
}

class TestReader implements Reader {
  private ObjectMapper mapper = new ObjectMapper();

  @Override
  public JsonNode getCriteria() throws JsonProcessingException {
    String criteria =
            "{\"criterias\":[{\"lastName\":\"Иванов\"},{\"minTimes\":1,\"productName\":\"milk\"},{\"minExpenses\":0,\"maxExpenses\":100},{\"badCustomers\":1}]}";
    return mapper.readTree(criteria).get("criterias");
  }

  @Override
  public Map<String, LocalDate> getDates() throws JsonProcessingException {
    String date = "{\"endDate\":\"2020-01-26\",\"startDate\":\"2000-01-01\"}";
    JsonNode res = mapper.readTree(date);
    Map<String, LocalDate> dates = new HashMap<>();
    dates.put("start", LocalDate.parse(res.get("startDate").asText()));
    dates.put("end", LocalDate.parse(res.get("endDate").asText()));
    return dates;
  }
}
