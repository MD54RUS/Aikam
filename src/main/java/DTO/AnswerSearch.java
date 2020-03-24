package DTO;

import java.util.ArrayList;
import java.util.List;

public class AnswerSearch {
    private String type = "search";
    private List<ResultsDTO> results;

    public AnswerSearch() {
        results = new ArrayList<>();
    }

    public void addResult(ResultsDTO resultsDTO) {
        results.add(resultsDTO);
    }

    public String getType() {
        return type;
    }

    public List<ResultsDTO> getResults() {
        return results;
    }
}
