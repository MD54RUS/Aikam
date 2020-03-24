import DAO.CustomerDAOImpl;
import DAO.GoodsDAOImpl;
import DAO.PurchasesDAOImpl;
import InutOutput.FileReaderImpl;
import InutOutput.FileWriterImpl;
import Service.BL;
import Service.DAOService;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {
    try {
      BL shema = new BL(
              new DAOService(new CustomerDAOImpl(), new GoodsDAOImpl(), new PurchasesDAOImpl()),
              new FileReaderImpl("input.json"),
              new FileWriterImpl("output.json"));
      shema.execute();
    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
  }
}
