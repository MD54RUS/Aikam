package InutOutput;

import DTO.CriteriaDTO;
import DTO.CriteriaDTOisJSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Criteria;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

public class FileReaderImpl {

  private File file;

  public FileReaderImpl(String filename) {
    file = new File(filename);
  }

  public List<Criteria> get() {
    ObjectMapper mapper = new ObjectMapper();
    List<Criteria> criteria = null;
    try {
      List<JSONObject> criteriaJSON = mapper.readValue(file, CriteriaDTOisJSON.class).getCriterias();
      criteria = mapper.readValue(file, CriteriaDTO.class).getCriterias();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return criteria;
  }
}
