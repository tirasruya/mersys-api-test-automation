package api.tests;

import api.base.BaseTest;
import api.endpoints.CityEndpoints;
import api.models.request.CityRequest;
import api.models.request.CountryIdRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class US102_CityCRUDTest extends BaseTest {

    private String cityId; // Dynamic ID to be passed between tests
    private final String countryId = "5cb0296bbc32694aad52993c"; // Switzerland ID for creation

    @Test(priority = 1, description = "Create a new city - POST")
    public void createCityTest() {
        LOGGER.info("Starting: Create City Test");

        CityRequest cityBody = new CityRequest();
        cityBody.setName("Angels City");
        cityBody.setCountry(new CountryIdRequest(countryId));

        LOGGER.info("Sending POST request to create city: {}", cityBody.getName());
        Response response = CityEndpoints.createCity(cityBody);
        logResponse(response);

        response.then().statusCode(201)
                .body("name", equalTo("Angels City"));

        cityId = response.jsonPath().getString("id");
        LOGGER.info("City successfully created with ID: {}", cityId);

        Assert.assertNotNull(cityId, "City ID should not be null!");
    }

    @Test(priority = 2, dependsOnMethods = "createCityTest", description = "Update an existing city - PUT")
    public void updateCityTest() {
        LOGGER.info("Starting: Update City Test for ID: {}", cityId);

        CityRequest updateBody = new CityRequest();
        updateBody.setId(cityId);
        updateBody.setName("Salatalık Şehri");
        updateBody.setCountry(new CountryIdRequest(countryId));

        LOGGER.info("Sending PUT request to update city name to: {}", updateBody.getName());
        Response response = CityEndpoints.updateCity(updateBody);
        logResponse(response);

        response.then().statusCode(200)
                .body("name", equalTo("Salatalık Şehri"));

        LOGGER.info("City successfully updated.");
    }

    @Test(priority = 3, description = "Filter cities by country - POST (Search)")
    public void listCitiesByCountryTest() {
        LOGGER.info("Starting: Filter Cities by Country Test");
        String turkeyId = "5cad7e76bc32694aad5298ce"; // Target country for filtering

        Map<String, Object> body = new HashMap<>();
        body.put("countryId", turkeyId);

        LOGGER.info("Sending Search request with countryId: {}", turkeyId);
        Response response = given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/school-service/api/cities/search");

        logResponse(response);
        response.then().statusCode(200);

        List<String> countryIds = response.jsonPath().getList("country.id");

        if (countryIds != null && !countryIds.isEmpty()) {
            LOGGER.info("Filter successful. Number of cities found: {}", countryIds.size());
            String actualId = countryIds.get(0);
            Assert.assertEquals(actualId, turkeyId, "Filter failed! Country ID mismatch.");
        } else {
            LOGGER.error("Filter returned no results for Country ID: {}", turkeyId);
            Assert.fail("No cities found for the specified country.");
        }
    }

    //, enabled = false
    @Test(priority = 4, description = "Delete the city - DELETE", dependsOnMethods = "createCityTest")
    public void deleteCityTest() {
        LOGGER.info("Starting: Delete City Test for ID: {}", cityId);

        Response response = CityEndpoints.deleteCity(cityId);
        logResponse(response);

        // API may return 200 or 204 depending on the system configuration
        response.then().assertThat().statusCode(oneOf(200, 204));

        LOGGER.info("City with ID: {} successfully deleted.", cityId);
    }
}