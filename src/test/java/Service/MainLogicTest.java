package Service;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


//Тестирование на Otj PG Embedded.
public class MainLogicTest {
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private EmbeddedPostgres pg;
    private String jdbcUrl;

    public MainLogicTest() {
    }

    @Before
    public void setUp() {
        try {
            pg = EmbeddedPostgres.builder().start();
            jdbcUrl = pg.getJdbcUrl("postgres", "postgres");
            Class.forName(DB_DRIVER);
            Connection connection = DriverManager.getConnection(jdbcUrl, "posgres", "posgres");
            InitDB.exe(connection);

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @After
    public void tearDown() {
        try {
            pg.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExecute() {

        //        ResultSet result = statement.executeQuery("select * from \"CUSTOMER\"");
        //        while (result.next()){
        //      System.out.println(result.getString("NAME"));
        //        }
    }

    @Test
    public void testExecuteStat() {
    }
}

class InitDB {
    static String initCustomerTable =
            "CREATE TABLE \"CUSTOMER\"\n"
                    + "("
                    + "    \"ID\" bigserial ,"
                    + "    \"NAME\" text COLLATE pg_catalog.\"default\","
                    + "    \"LASTNAME\" text COLLATE pg_catalog.\"default\","
                    + "    CONSTRAINT \"CUSTOMER_pkey\" PRIMARY KEY (\"ID\")"
                    + ")"
                    + "TABLESPACE pg_default;";
    static String initGoodsTable =
            "CREATE TABLE \"GOODS\""
                    + "("
                    + "    \"ID\" bigserial,"
                    + "    \"NAME\" text COLLATE pg_catalog.\"default\","
                    + "    \"PRICE\" integer,\n"
                    + "    CONSTRAINT \"GOODS_pkey\" PRIMARY KEY (\"ID\")"
                    + ")"
                    + "TABLESPACE pg_default;";
    static String initTablePurchases =
            "CREATE TABLE \"PURCHASES\""
                    + "("
                    + "    \"ID\" bigserial,"
                    + "    \"CUSTOMER_ID\" bigint NOT NULL,"
                    + "    \"GOODS_ID\" bigint NOT NULL,"
                    + "    \"DATE\" date NOT NULL,"
                    + "    CONSTRAINT \"PURCHASES_pkey\" PRIMARY KEY (\"ID\")"
                    + ")"
                    + "TABLESPACE pg_default;";
    static String loadCustomerData =
            "insert into \"CUSTOMER\" (\"ID\", \"NAME\", \"LASTNAME\") values"
                    + "('1', 'Иван', 'Иванов'),"
                    + "('2', 'Петр', 'Петров'),"
                    + "('3', 'Сидор', 'Сидоров');";
    static String loadGoodsData =
            "insert into \"GOODS\" (\"ID\", \"NAME\", \"PRICE\") values"
                    + "('1', 'apple', '12'),"
                    + "('2', 'milk', '45');";
    static String loadPurchaseData =
            "insert into \"PURCHASES\" (\"ID\", \"CUSTOMER_ID\", \"GOODS_ID\", \"DATE\") values"
                    + "('1','1','1','2001-01-01'),"
                    + "('2','2','1','2002-02-02'),"
                    + "('3','1','1','2001-01-02'),"
                    + "('5','3','2','2002-02-02'),"
                    + "('6','3','1','2001-01-01'),"
                    + "('7','1','2','2001-01-01');";

    static void exe(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(InitDB.initCustomerTable);
        statement.execute(InitDB.initGoodsTable);
        statement.execute(InitDB.initTablePurchases);
        statement.execute(InitDB.loadCustomerData);
        statement.execute(InitDB.loadGoodsData);
        statement.execute(InitDB.loadPurchaseData);
    }
}
