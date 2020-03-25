package Service;

import DTO.AnswerSearch;
import DTO.ResultStringDTO;
import InputOutput.Reader;
import InputOutput.Writer;
import commands.CommandExecutor;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MLString {
  private Reader reader;
  private Writer writer;
  private AnswerSearch answer;

  public MLString(Reader reader, Writer writer) {
    this.reader = reader;
    this.writer = writer;
    answer = new AnswerSearch();
  }

  public void execute() throws IOException, ParseException {
    List<JSONObject> conditions = reader.get();
    if (conditions != null) {
      conditions.forEach(
              criteria -> {
                try {
                  CommandExecutor query = ExecuterSupplier.get(criteria);
                  assert query != null;
                  answer.addResult(new ResultStringDTO(criteria, query.execute()));
                } catch (SQLException e) {
                  e.printStackTrace();
                }
              });
    }
    writer.write(answer);
  }
}
