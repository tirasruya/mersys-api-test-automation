package api.models.request;

public class StateRequest {

    private String name;
    private String shortName;

    public StateRequest(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }
}