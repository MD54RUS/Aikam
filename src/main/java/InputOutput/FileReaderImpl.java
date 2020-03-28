package InputOutput;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReaderImpl implements Reader {

  private String filename;
  private JSONParser parser;

  public FileReaderImpl(String filename) {
    parser = new JSONParser();
    this.filename = filename;
  }

  @Override
  public List<JSONObject> getCriteria() throws IOException, ParseException {
    Object obj = parser.parse(new FileReader(filename));
    JSONObject jsonObject = (JSONObject) obj;
    JSONArray criteria = (JSONArray) jsonObject.get("criterias");
    return new ArrayList<JSONObject>(criteria);
  }

  @Override
  public Map<String, LocalDate> getDates() throws IOException, ParseException {
    JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filename));
    LocalDate start = LocalDate.parse((String) jsonObject.get("startDate"));
    LocalDate end = LocalDate.parse((String) jsonObject.get("endDate"));
    if (!(start.compareTo(end) < 0)) {
      throw new RuntimeException("Даты должны быть заданы в порядке возрастания!");
    }
    Map<String, LocalDate> dates = new HashMap<>();
    dates.put("start", start);
    dates.put("end", end);
    return dates;
  }
}
