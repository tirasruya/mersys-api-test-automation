package api.models.request;

import java.util.List;

public class CountryRequest {

    private String name;
    private String code;
    private boolean hasState;
    private List<StateRequest> states;

    public CountryRequest(String name, String code, boolean hasState, List<StateRequest> states) {
        this.name = name;
        this.code = code;
        this.hasState = hasState;
        this.states = states;
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

    public List<StateRequest> getStates() {
        return states;
    }
}
