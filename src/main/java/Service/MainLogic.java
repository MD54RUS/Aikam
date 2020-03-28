package Service;

import DTO.*;
import InputOutput.Reader;
import InputOutput.Writer;
import com.fasterxml.jackson.databind.JsonNode;
import commands.QueryExecutorStatistics;
import commands.QueryExecutorSupplier;
import commands.SqlCommandExecutor;
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
  private AnswerTemplate answer;

  public MainLogic(Reader reader, Writer writer) {
    logger = LoggerFactory.getLogger(MainLogic.class);
    this.reader = reader;
    this.writer = writer;
  }

  // логика исполнения с ключем search
  public void executeSearch() {
    try {
      JsonNode conditions = reader.getCriteria();
      List<CriteriaResult> results = new ArrayList<>();
      if (conditions != null) {
        for (int i = 0; i < conditions.size(); i++) {
          JsonNode criterion = conditions.get(i);
          SqlCommandExecutor query = QueryExecutorSupplier.get(criterion);
          results.add(new CriteriaResult(criterion, query.execute()));
        }
      }
      answer = new AnswerSearchDTO(results);
    } catch (IOException | SQLException e) {
      logger.error("Search execute error: ", e);
      answer = new AnswerErrorDTO(e.getMessage());
    }
    writer.write(answer);
  }

  // логика исполнения с ключем stat
  public void executeStat() {
    try {
      Map<String, LocalDate> dates = reader.getDates();
      QueryExecutorStatistics stat = new QueryExecutorStatistics(dates);
      answer = new AnswerStatisticsDTO(dates, stat.execute());
    } catch (IOException | SQLException | NullPointerException e) {
      logger.error("Statistics execute error: ", e);
      answer = new AnswerErrorDTO(e.getMessage());
    }
    writer.write(answer);
  }
}
