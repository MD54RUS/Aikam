package InutOutput;

import DTO.CriteriaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Criteria;

import java.io.File;
import java.util.List;

public class FileReaderImpl implements Reader {

  private File file;

  public FileReaderImpl(String filename) {
    file = new File(filename);
  }

  public List<Criteria> get() {
    ObjectMapper mapper = new ObjectMapper();
    List<Criteria> criteria = null;
    try {
      criteria = mapper.readValue(file, CriteriaDTO.class).getCriterias();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return criteria;
  }
}
