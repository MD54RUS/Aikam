package InputOutput;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface Reader {
    //Получение набора критериев для запросов
    List<JSONObject> getCriteria() throws IOException, ParseException;

    //Получение пары дат для статистики
    Map<String, LocalDate> getDates() throws IOException, ParseException;
}
