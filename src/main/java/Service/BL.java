package Service;

import DTO.AnswerSearch;
import DTO.ResultsDTO;
import InutOutput.Reader;
import InutOutput.Writer;
import entity.Criteria;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BL {
  private DAOService daoService;
  private Reader reader;
  private Writer writer;
  private AnswerSearch answer;

  public BL(DAOService daoService, Reader reader, Writer writer) {
    this.daoService = daoService;
    this.reader = reader;
    this.writer = writer;
    answer = new AnswerSearch();
  }

//  public void execute() throws IOException {
//    List<Criteria> conditions = reader.get();
//    if (conditions != null) {
//      conditions.forEach(
//              criteria -> {
//                try {
//                  answer.addResult(new ResultsDTO(criteria, daoService.search(criteria)));
//                } catch (SQLException e) {
//                  e.printStackTrace();
//                }
//              });
//    }
//    System.out.println(answer);
//    writer.write(answer);
//  }
}
