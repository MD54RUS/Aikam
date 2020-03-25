package Service;

import DTO.*;
import InputOutput.Reader;
import InputOutput.Writer;
import commands.CommandExecutor;
import commands.Statistics;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MainLogic {
  private Reader reader;
  private Writer writer;
  private Answer answer;

  public MainLogic(Reader reader, Writer writer) {
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
      e.printStackTrace();
    }
    try {
      writer.write(answer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void executeStat() {
    Answer answer;
    try {
      LocalDate start =
              reader.getStat().getKey().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      LocalDate end =
              reader.getStat().getValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      Statistics stat = new Statistics(start, end);
      answer = new AnswerStatisticsDTO(start, end, stat.execute());
    } catch (ParseException | IOException | SQLException e) {
      answer = new AnswerErrorDTO(e.getMessage());
      e.printStackTrace();
    }
    try {
      writer.write(answer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
