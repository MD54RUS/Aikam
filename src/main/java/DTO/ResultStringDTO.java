package DTO;

import entity.Customer;
import org.json.simple.JSONObject;

import java.util.List;

public class ResultStringDTO {
    private JSONObject criteria;
    private List<Customer> results;

    public ResultStringDTO(JSONObject criteria, List<Customer> results) {
        this.criteria = criteria;
        this.results = results;
    }

    public JSONObject getCriteria() {
        return criteria;
    }

    public List<Customer> getResults() {
        return results;
    }
}
