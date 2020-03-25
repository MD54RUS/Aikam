package DTO;

import java.util.ArrayList;
import java.util.List;

public class AnswerSearch {
    private String type = "search";
    private List<ResultStringDTO> results;

    public AnswerSearch() {
        results = new ArrayList<>();
    }

    public void addResult(ResultStringDTO resultsDTO) {
        results.add(resultsDTO);
    }

    public String getType() {
        return type;
    }

    public List<ResultStringDTO> getResults() {
        return results;
    }
}
