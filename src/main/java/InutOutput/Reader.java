package InutOutput;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface Reader {
    List<JSONObject> get() throws IOException, ParseException;
}
