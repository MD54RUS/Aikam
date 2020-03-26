package Service;

import DTO.*;
import InputOutput.Reader;
import InputOutput.Writer;
import commands.CommandExecutor;
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

public class MainLogic {
  Logger logger;
  private Reader reader;
  private Writer writer;
  private Answer answer;

  public MainLogic(Reader reader, Writer writer) {
    logger = LoggerFactory.getLogger(MainLogic.class);
    this.reader = reader;
    this.writer = writer;
  }

  public void execute() {
    try {
      List<JSONObject> conditions = reader.get();
      List<CriteriaResult> results = new ArrayList<>();

      if (conditions != null) {
        for (JSONObject criteria : conditions) {
          CommandExecutor query = ExecuterSupplier.get(criteria);
          assert query != null;
          results.add(new CriteriaResult(criteria, query.execute()));
        }
      }
      answer = new AnswerSearchDTO(results);
    } catch (ParseException | IOException | SQLException e) {
      answer = new AnswerErrorDTO(e.getMessage());
      logger.error("Search execute", e);
    }
    writer.write(answer);
  }

  public void executeStat() {
    try {
      LocalDate start = reader.getStat().getKey();
      LocalDate end = reader.getStat().getValue();
      Statistics stat = new Statistics(start, end);
      answer = new AnswerStatisticsDTO(start, end, stat.execute());
    } catch (ParseException | IOException | SQLException e) {
      answer = new AnswerErrorDTO(e.getMessage());
      logger.error("Statistics execute", e);
    }
    writer.write(answer);
  }
}
