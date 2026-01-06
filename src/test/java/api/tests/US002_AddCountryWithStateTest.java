package api.tests;

import api.base.BaseTest;
import api.endpoints.CountryEndpoints;
import api.models.request.CountryRequest;
import api.models.request.StateRequest;
import api.models.response.CountryResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class US002_AddCountryWithStateTest extends BaseTest {

    @Test
    public void addCountryWithStateSuccessfully() {

        LOGGER.info("US002 - Add Country With State test started");

        int random = new Random().nextInt(10000);

        String countryName = "Country-" + random;
        String countryCode = "C" + random;
        String stateName = "Init-State-" + random;
        String stateShort = "IS" + random;

        LOGGER.info("Generated random data: {}, {}, {}, {}",
                countryName, countryCode, stateName, stateShort);

        StateRequest stateRequest =
                new StateRequest(stateName, stateShort);

        CountryRequest countryRequest =
                new CountryRequest(
                        countryName,
                        countryCode,
                        true,
                        List.of(stateRequest)
                );

        CountryResponse response =
                request
                        .body(countryRequest)
                        .when()
                        .post(CountryEndpoints.ADD_COUNTRY)
                        .then()
                        .statusCode(201)
                        .extract()
                        .as(CountryResponse.class);

        Assert.assertNotNull(response.getId(), "Country ID is null");
        Assert.assertEquals(response.isHasState(), true);
        Assert.assertEquals(response.getName(), countryName);
        Assert.assertEquals(response.getCode(), countryCode);

        LOGGER.info("Country created with ID: {}", response.getId());
        LOGGER.info("US002 - Add Country With State test finished");
    }
}