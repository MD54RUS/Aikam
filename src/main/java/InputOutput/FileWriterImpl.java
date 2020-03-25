package InputOutput;

import DTO.AnswerSearch;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FileWriterImpl implements Writer {
  private File file;

  public FileWriterImpl(String filename) {
    file = new File(filename);
  }

  public void write(AnswerSearch answer) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(file, answer);
  }
}
