package InputOutput;

import DTO.Answer;
import DTO.AnswerSearchDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FileWriterImpl implements Writer {
  private File file;

  public FileWriterImpl(String filename) {
    file = new File(filename);
  }

  public void write(Answer answer) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(file, answer);
  }
}
