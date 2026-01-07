package api.models.request;

public class CreateStateRequest {

    private String name;
    private String shortName;
    private CountryRef country;

    public CreateStateRequest(String name, String shortName, String countryId) {
        this.name = name;
        this.shortName = shortName;
        this.country = new CountryRef(countryId);
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public CountryRef getCountry() {
        return country;
    }

    public static class CountryRef {
        private String id;

        public CountryRef(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
}