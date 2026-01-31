package api.models.request;

public class IncidentTypeRequest {
    private String id;
    private String name;
    private String minScore;
    private String maxScore;

    public IncidentTypeRequest() {}

    public IncidentTypeRequest(String name, String minScore, String maxScore) {
        this.name = name;
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    // getter-setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMinScore() { return minScore; }
    public void setMinScore(String minScore) { this.minScore = minScore; }

    public String getMaxScore() { return maxScore; }
    public void setMaxScore(String maxScore) { this.maxScore = maxScore; }
}
