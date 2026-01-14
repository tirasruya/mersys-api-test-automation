package api.models.request;

public class CountryIdRequest {
    private String id;

    public CountryIdRequest(String id) {
        this.id = id;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}
