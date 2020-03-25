package DTO;

import entity.Customer;
import org.json.simple.JSONObject;

import java.util.List;

public class CriteriaResult {
    private JSONObject criteria;
    private List<Customer> results;

    public CriteriaResult(JSONObject criteria, List<Customer> results) {
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
