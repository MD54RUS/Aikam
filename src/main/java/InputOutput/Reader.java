package InputOutput;

import javafx.util.Pair;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface Reader {
    List<JSONObject> get() throws IOException, ParseException;

    Pair<LocalDate, LocalDate> getStat() throws IOException, ParseException;
}
