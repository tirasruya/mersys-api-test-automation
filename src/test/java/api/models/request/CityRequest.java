package api.models.request;

public class CityRequest {

    private String id;
    private String name;
    private CountryIdRequest country;

    public CityRequest() {}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public CountryIdRequest getCountry() {return country;}

    public void setCountry(CountryIdRequest country) {this.country = country;}

}
