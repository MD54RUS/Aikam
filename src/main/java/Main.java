import DAO.CustomerDAOImpl;
import DAO.GoodsDAOImpl;
import DAO.PurchasesDAOImpl;
import InutOutput.FileReaderImpl;
import InutOutput.FileReaderToStringImpl;
import InutOutput.FileWriterImpl;
import Service.BL;
import Service.DAOService;
import Service.MLString;
import commands.CommandExecutor;
import commands.CustomersByLastname;
import commands.PassiveCustomers;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {
      try {
          MLString shema = new MLString(
                  new DAOService(new CustomerDAOImpl(), new GoodsDAOImpl(), new PurchasesDAOImpl()),
                  new FileReaderToStringImpl("input.json"),
                  new FileWriterImpl("output.json"));
          shema.execute();
      } catch (SQLException | IOException | ParseException e) {
          e.printStackTrace();
      }
//    try {
//      CommandExecutor executor = new PassiveCustomers(1);
//      System.out.println(executor.execute());
//      executor = new CustomersByLastname("Сидоров");
//      System.out.println(executor.execute());
//    } catch (SQLException e) {
//      System.out.println("Executor died");
//      e.printStackTrace();
//    }
  }
}
