package api.tests;

import api.base.BaseTest;
import api.endpoints.IncidentTypeEndpoints;
import api.models.request.IncidentTypeRequest;
import api.models.response.IncidentTypeResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class US110_IncidentTypeCRUDTest extends BaseTest {

    private String incidentId;

    @Test(priority = 1)
    public void createIncidentType() {
        int rnd = new Random().nextInt(10_000);

        IncidentTypeRequest body = new IncidentTypeRequest(
                "US110-Incident-" + rnd,
                "1",
                "10"
        );

        Response response =
                request
                        .body(body)
                        .post(IncidentTypeEndpoints.CREATE);

        logResponse(response);
        Assert.assertEquals(response.statusCode(), 201);

        IncidentTypeResponse res = response.as(IncidentTypeResponse.class);
        incidentId = res.getId();
        Assert.assertNotNull(incidentId);
    }

    @Test(priority = 2, dependsOnMethods = "createIncidentType")
    public void updateIncidentType() {
        IncidentTypeRequest body = new IncidentTypeRequest("US110-Incident-Updated", "2", "9");
        body.setId(incidentId);

        Response response =
                request
                        .body(body)
                        .put(IncidentTypeEndpoints.UPDATE);

        logResponse(response);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 3, dependsOnMethods = "createIncidentType")
    public void deleteIncidentType() {
        Response response =
                request
                        .pathParam("id", incidentId)
                        .delete(IncidentTypeEndpoints.DELETE);

        logResponse(response);
        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 204);
    }
}
