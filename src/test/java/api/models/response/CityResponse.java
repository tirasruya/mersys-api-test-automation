package api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityResponse {
    private String id;
    private String name;
    private CountryResponse country; // İç içe obje için yeni class'ı kullanıyoruz

    public CityResponse() {}

    // Getter ve Setterlar
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public CountryResponse getCountry() { return country; }
    public void setCountry(CountryResponse country) { this.country = country; }
}