package InputOutput;

import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileReaderImpl implements Reader {

  private String filename;
  JSONParser parser;

  public FileReaderImpl(String filename) {
    parser = new JSONParser();
    this.filename = filename;
  }

  public List<JSONObject> get() throws IOException, ParseException {
    Object obj = parser.parse(new FileReader(filename));
    JSONObject jsonObject = (JSONObject) obj;
    JSONArray criterias = (JSONArray) jsonObject.get("criterias");
    return new ArrayList<JSONObject>(criterias);
  }

  @Override
  public Pair<Date, Date> getStat() throws IOException, ParseException {
    JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filename));
    return new Pair<>((Date) jsonObject.get("startDate"), (Date) jsonObject.get("endDate"));
  }
}
