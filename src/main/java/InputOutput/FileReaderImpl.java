package InputOutput;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FileReaderImpl implements Reader {

  private String filename;
  private ObjectMapper mapper;

  public FileReaderImpl(String filename) {
    mapper = new ObjectMapper();
    this.filename = filename;
  }

  @Override
  public JsonNode getCriteria() throws IOException {
    JsonNode res = mapper.readTree(new File(filename));
    return res.get("criterias");
  }

  @Override
  public Map<String, LocalDate> getDates() throws IOException {
    JsonNode res = mapper.readTree(new File(filename));

    LocalDate start = LocalDate.parse(res.get("startDate").asText());
    LocalDate end = LocalDate.parse(res.get("endDate").asText());
    if (!(start.compareTo(end) < 0)) {
      throw new RuntimeException("Даты должны быть заданы в порядке возрастания!");
    }
    Map<String, LocalDate> dates = new HashMap<>();
    dates.put("start", start);
    dates.put("end", end);
    return dates;
  }
}
