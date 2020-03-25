import InputOutput.FileReaderToStringImpl;
import InputOutput.FileWriterImpl;
import Service.MLString;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    try {
      MLString schema =
              new MLString(new FileReaderToStringImpl("input.json"), new FileWriterImpl("output.json"));
      schema.execute();
    } catch (IOException | ParseException e) {
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
