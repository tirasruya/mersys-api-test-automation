package api.models.request;

public class UpdateStateRequest {

    private String id;
    private String name;
    private String shortName;

    public UpdateStateRequest(String id, String name, String shortName) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }
}