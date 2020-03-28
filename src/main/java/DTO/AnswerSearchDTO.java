package DTO;

import java.util.ArrayList;
import java.util.List;

public class AnswerSearchDTO extends AnswerTemplate {
    private String type = "search";
    private List<CriteriaResult> results;

    public AnswerSearchDTO() {
        results = new ArrayList<>();
    }

    public AnswerSearchDTO(List<CriteriaResult> results) {
        this.results = results;
    }

  public void addResult(CriteriaResult resultsDTO) {
    results.add(resultsDTO);
  }

  // Нужен для сериализации
  public List<CriteriaResult> getResults() {
    return results;
  }

  public String getType() {
    return type;
  }
}
