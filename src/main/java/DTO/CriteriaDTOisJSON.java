package DTO;


import org.json.JSONObject;

import java.util.List;

public class CriteriaDTOisJSON {
    private List<JSONObject> criterias;

    public List<JSONObject> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<JSONObject> criterias) {
        this.criterias = criterias;
    }
}
