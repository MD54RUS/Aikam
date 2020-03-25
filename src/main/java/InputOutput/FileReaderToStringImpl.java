package InputOutput;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderToStringImpl implements Reader {

  private String filename;

  public FileReaderToStringImpl(String filename) {
    this.filename = filename;
  }

  public List<JSONObject> get() throws IOException, ParseException {

    JSONParser parser = new JSONParser();
    Object obj = parser.parse(new FileReader(filename));
    JSONObject jsonObject = (JSONObject) obj;
    JSONArray criterias = (JSONArray) jsonObject.get("criterias");
    return new ArrayList<JSONObject>(criterias);
  }
}
