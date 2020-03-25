import InputOutput.FileReaderImpl;
import InputOutput.FileWriterImpl;
import Service.MainLogic;

public class Main {
  public static void main(String[] args) {
    MainLogic schema =
            new MainLogic(new FileReaderImpl(args[1]), new FileWriterImpl(args[2]));

    switch (args[0]) {
      case "search":
        schema.execute();
        break;
      case "stat":
        schema.executeStat();
        break;
      default:
        throw new RuntimeException("Не правильно заданы аргументы коммандной строки");
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
