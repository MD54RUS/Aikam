import InputOutput.FileReaderImpl;
import InputOutput.FileWriterImpl;
import Service.MainLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(Main.class);
    MainLogic schema = new MainLogic(new FileReaderImpl(args[1]), new FileWriterImpl(args[2]));
    logger.debug(Arrays.toString(args));
    switch (args[0]) {
      case "search":
        schema.executeSearch();
        break;
      case "stat":
        schema.executeStat();
        break;
      default:
        logger.error("Invalid arguments = {}", Arrays.toString(args));
        System.out.println("Не правильно заданы аргументы коммандной строки");
    }
  }
}
