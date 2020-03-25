package InputOutput;

import javafx.util.Pair;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface Reader {
    List<JSONObject> get() throws IOException, ParseException;

    Pair<Date, Date> getStat() throws IOException, ParseException;
}
