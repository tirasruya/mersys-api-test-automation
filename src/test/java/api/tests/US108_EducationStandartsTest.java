package api.tests;

import api.base.BaseTest;
import api.endpoints.EduStandartsEndpoints;
import api.models.request.EduStandartsRequest;
import api.models.response.EduStandartsResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class US108_EducationStandartsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(US108_EducationStandartsTest.class);
    private String educationStandardId;

    @BeforeMethod
    public void resetRequest() {
        logger.info("Initializing RequestSpecification and resetting parameters for the next test case.");
        request = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth().oauth2(token);
    }

    @Test(priority = 1, description = "Negative Test: Verify system rejects name longer than 100 characters")
    public void createStandardWithLongName() {
        logger.info("Starting Negative Test: Creating Education Standard with >100 characters name.");
        String longName = "A".repeat(101);

        EduStandartsRequest longNameBody = new EduStandartsRequest(
                longName, "Valid description", "5fe39e45fa018a418441235a",
                "6582cf10a5c6896263590505", "6582cf61a5c6896263590507"
        );

        Response response = request.body(longNameBody).when().post(EduStandartsEndpoints.CREATE);
        logResponse(response);

        if (response.statusCode() == 201) {
            logger.error("BUG DETECTED: System allowed name longer than 100 characters! Status: 201");
        }
        Assert.assertEquals(response.statusCode(), 400, "Should return 400 Bad Request for exceeding name limit.");
    }

    @Test(priority = 2, description = "Negative Test: Verify system rejects description longer than 5000 characters")
    public void createStandardWithLongDescription() {
        logger.info("Starting Negative Test: Creating Education Standard with >5000 characters description.");
        String longDescription = "D".repeat(5001);

        EduStandartsRequest longDescBody = new EduStandartsRequest(
                "Valid Name", longDescription, "5fe39e45fa018a418441235a",
                "6582cf10a5c6896263590505", "6582cf61a5c6896263590507"
        );

        Response response = request.body(longDescBody).when().post(EduStandartsEndpoints.CREATE);
        logResponse(response);

        if (response.statusCode() == 201) {
            logger.error("BUG DETECTED: System allowed description longer than 5000 characters! Status: 201");
        }
        Assert.assertEquals(response.statusCode(), 400, "Should return 400 Bad Request for exceeding description limit.");
    }

    @Test(priority = 3, description = "Positive Test: Create a valid Education Standard")
    public void createEducationStandard() {
        logger.info("Starting Positive Test: Creating a new valid Education Standard.");
        EduStandartsRequest createBody = new EduStandartsRequest(
                "SDET Academy Standard", "Description for SDET Standard",
                "5fe39e45fa018a418441235a", "6582cf10a5c6896263590505", "6582cf61a5c6896263590507"
        );

        Response response = request.body(createBody).when().post(EduStandartsEndpoints.CREATE);
        logResponse(response);

        Assert.assertEquals(response.statusCode(), 201, "Expected 201 Created for valid data.");

        EduStandartsResponse responseBody = response.as(EduStandartsResponse.class);
        educationStandardId = responseBody.getId();
        logger.info("Education Standard successfully created with ID: {}", educationStandardId);

        Assert.assertNotNull(educationStandardId, "Created ID must not be null.");
    }

    @Test(priority = 4, dependsOnMethods = "createEducationStandard", description = "Positive Test: Update existing Education Standard")
    public void updateEducationStandard() {
        logger.info("Starting Positive Test: Updating Education Standard with ID: {}", educationStandardId);
        EduStandartsRequest updateBody = new EduStandartsRequest(
                "SDET Academy Standard - Updated", "Updated Description",
                "5fe39e45fa018a418441235a", "6582cf10a5c6896263590505", "6582cf61a5c6896263590507"
        );
        updateBody.setId(educationStandardId);

        Response response = request.body(updateBody).when().put(EduStandartsEndpoints.UPDATE);
        logResponse(response);

        Assert.assertEquals(response.statusCode(), 200, "Expected 200 OK for successful update.");
        logger.info("Education Standard with ID: {} successfully updated.", educationStandardId);
    }

    @Test(priority = 5, dependsOnMethods = "createEducationStandard", description = "Cleanup: Delete created Education Standard")
    public void deleteEducationStandard() {
        logger.info("Starting Cleanup: Deleting Education Standard with ID: {}", educationStandardId);
        Response response = request.pathParam("id", educationStandardId).when().delete(EduStandartsEndpoints.DELETE);
        logResponse(response);

        int status = response.statusCode();
        Assert.assertTrue(status == 200 || status == 204, "Expected 200 or 204 for successful deletion.");
        logger.info("Education Standard with ID: {} successfully deleted (Status: {}).", educationStandardId, status);
    }

    @Test(priority = 6, description = "Read Test: Verify listing standards for a school (Potential Bug)")
    public void listEducationStandards() {
        String schoolId = "5fe39e45fa018a418441235a";
        logger.info("Starting Read Test: Listing standards for School ID: {}", schoolId);

        Response response = request.pathParam("schoolId", schoolId).when().get(EduStandartsEndpoints.LIST);
        logResponse(response);

        if (response.statusCode() == 404) {
            logger.error("BUG DETECTED: Endpoint /school/{schoolId} returned 404 Not Found!");
        }

        Assert.assertEquals(response.statusCode(), 200, "AC states status 200, but system returned " + response.statusCode());
    }
}
