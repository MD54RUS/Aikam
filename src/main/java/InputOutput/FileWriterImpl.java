package InputOutput;

import DTO.AnswerTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class FileWriterImpl implements Writer {
  private File file;

  public FileWriterImpl(String filename) {
    file = new File(filename);
  }

  public void write(AnswerTemplate answer) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      mapper.writeValue(file, answer);
    } catch (IOException e) {
      Logger logger = LoggerFactory.getLogger(Writer.class);
      logger.error("Cant save result", e);
      System.out.println(String.format("Cant save result in file %s", file.getName()));
    }
  }
}
