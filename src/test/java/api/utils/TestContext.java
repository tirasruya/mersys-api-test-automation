package api.utils;

public class TestContext {

    private static String countryId;
    private static String stateId;

    public static String getCountryId() {
        return countryId;
    }

    public static void setCountryId(String countryId) {
        TestContext.countryId = countryId;
    }

    public static String getStateId() {
        return stateId;
    }

    public static void setStateId(String stateId) {
        TestContext.stateId = stateId;
    }
}