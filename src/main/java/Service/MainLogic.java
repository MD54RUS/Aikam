package Service;

import DTO.*;
import InputOutput.Reader;
import InputOutput.Writer;
import commands.CommandExecutor;
import commands.ExecuterSupplier;
import commands.Statistics;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainLogic {
  private Logger logger;
  private Reader reader;
  private Writer writer;
  private Answer answer;

  public MainLogic(Reader reader, Writer writer) {
    logger = LoggerFactory.getLogger(MainLogic.class);
    this.reader = reader;
    this.writer = writer;
  }

  //логика исполнения с ключем search
  public void executeSearch() {
    try {
      List<JSONObject> conditions = reader.getCriteria();
      List<CriteriaResult> results = new ArrayList<>();
      if (conditions != null) {
        for (JSONObject criterion : conditions) {
          CommandExecutor query = ExecuterSupplier.get(criterion);
          results.add(new CriteriaResult(criterion, query.execute()));
        }
      }
      answer = new AnswerSearchDTO(results);
    } catch (ParseException | IOException | SQLException e) {
      logger.error("Search execute error: ", e);
      answer = new AnswerErrorDTO(e.getMessage());
    }
    writer.write(answer);
  }

  //логика исполнения с ключем stat
  public void executeStat() {
    try {

      Map<String, LocalDate> dates = reader.getDates();
      Statistics stat = new Statistics(dates);
      answer = new AnswerStatisticsDTO(dates, stat.execute());
    } catch (ParseException | IOException | SQLException | NullPointerException e) {
      logger.error("Statistics execute error: ", e);
      answer = new AnswerErrorDTO(e.getMessage());
    }
    writer.write(answer);
  }
}
