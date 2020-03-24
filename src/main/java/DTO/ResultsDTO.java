package DTO;

import entity.Criteria;
import entity.Customer;

import java.util.List;

public class ResultsDTO {
    private Criteria criteria;
    private List<Customer> results;

    public ResultsDTO(Criteria criteria, List<Customer> results) {
        this.criteria = criteria;
        this.results = results;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public List<Customer> getResults() {
        return results;
    }
}
