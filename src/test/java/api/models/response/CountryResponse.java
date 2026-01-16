package api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryResponse {

    private String id;
    private String name;
    private String code;
    private boolean hasState;
    private List<StateResponse> states;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public boolean isHasState() {
        return hasState;
    }

    public List<StateResponse> getStates() {
        return states;
    }
}