import InputOutput.FileReaderImpl;
import InputOutput.FileWriterImpl;
import InputOutput.Reader;
import InputOutput.Writer;
import Service.MainLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(Main.class);
    Reader reader = new FileReaderImpl(args[1]);
    Writer writer = new FileWriterImpl(args[2]);
    MainLogic schema = new MainLogic(reader, writer);
    logger.debug(Arrays.toString(args));
    switch (args[0]) {
      case "search":
        schema.execute();
        break;
      case "stat":
        schema.executeStat();
        break;
      default:
        logger.error("Invalid arguments = {}", Arrays.toString(args));
        throw new RuntimeException("Не правильно заданы аргументы коммандной строки");
    }
  }
}
