package api.tests;

import api.base.BaseTest;
import api.endpoints.CountryEndpoints;
import api.endpoints.StateEndpoints;
import api.models.request.CountryRequest;
import api.models.request.CreateStateRequest;
import api.models.request.UpdateStateRequest;
import api.models.response.CountryResponse;
import api.models.response.StateResponse;
import api.utils.TestContext;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class US101_StateCRUDTest extends BaseTest {

    private String stateId;

    @BeforeClass
    public void ensureCountryExists() {

        if (TestContext.getCountryId() != null) {
            LOGGER.info(
                    "US101 | Country already exists in TestContext. countryId={}",
                    TestContext.getCountryId()
            );
            return;
        }

        LOGGER.info("US101 | No country found in TestContext. Creating new country");

        int random = new Random().nextInt(10_000);

        CountryRequest requestBody =
                new CountryRequest(
                        "US101-Country-" + random,
                        "UC" + random,
                        true,
                        null
                );

        CountryResponse response =
                request
                        .body(requestBody)
                        .post(CountryEndpoints.ADD_COUNTRY)
                        .then()
                        .statusCode(201)
                        .extract()
                        .as(CountryResponse.class);

        TestContext.setCountryId(response.getId());

        LOGGER.info(
                "US101 | Country created successfully. countryId={}",
                response.getId()
        );
    }

    @Test(priority = 1)
    public void createState() {

        int random = new Random().nextInt(10_000);
        String stateName = "US101-State-" + random;
        String shortName = "U" + random;
        String countryId = TestContext.getCountryId();

        Assert.assertNotNull(countryId, "CountryId not found in TestContext");

        LOGGER.info(
                "US101 | Creating state. name={}, shortName={}, countryId={}",
                stateName, shortName, countryId
        );

        CreateStateRequest requestBody =
                new CreateStateRequest(stateName, shortName, countryId);

        Response response =
                request
                        .body(requestBody)
                        .post(StateEndpoints.CREATE_STATE);

        logResponse(response);

        Assert.assertEquals(response.statusCode(), 201);

        StateResponse stateResponse = response.as(StateResponse.class);
        stateId = stateResponse.getId();

        Assert.assertNotNull(stateId, "StateId is null after create");

        LOGGER.info(
                "US101 | State created successfully. stateId={}",
                stateId
        );
    }

    @Test(priority = 2)
    public void getAllStates() {

        LOGGER.info("US101 | Fetching all states");

        Response response =
                request
                        .get(StateEndpoints.GET_ALL_STATES);

        logResponse(response);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(
                response.time() < 1000,
                "Response time exceeded 1000ms"
        );

        LOGGER.info(
                "US101 | States fetched successfully in {} ms",
                response.time()
        );
    }

    @Test(priority = 3)
    public void updateState() {

        Assert.assertNotNull(stateId, "StateId is null. Create state failed.");

        int random = new Random().nextInt(10_000);
        String newName = "State-PATCH-" + random;
        String newShortName = "U" + random;

        LOGGER.info(
                "US101 | Updating state. stateId={}, newName={}",
                stateId, newName
        );

        UpdateStateRequest requestBody =
                new UpdateStateRequest(
                        stateId,
                        newName,
                        newShortName
                );

        Response response =
                request
                        .pathParam("id", stateId)
                        .body(requestBody)
                        .put(StateEndpoints.UPDATE_STATE);

        logResponse(response);

        Assert.assertEquals(response.statusCode(), 200);

        StateResponse updatedState = response.as(StateResponse.class);
        Assert.assertEquals(updatedState.getName(), newName);

        LOGGER.info(
                "US101 | State updated successfully. stateId={}",
                stateId
        );
    }

    @Test(priority = 4)
    public void deleteState() {

        Assert.assertNotNull(stateId, "StateId is null. Create state failed.");

        LOGGER.info(
                "US101 | Deleting state. stateId={}",
                stateId
        );

        Response response =
                request
                        .pathParam("id", stateId)
                        .delete(StateEndpoints.DELETE_STATE);

        logResponse(response);

        Assert.assertEquals(response.statusCode(), 200);

        LOGGER.info(
                "US101 | State deleted successfully. stateId={}",
                stateId
        );
    }
}