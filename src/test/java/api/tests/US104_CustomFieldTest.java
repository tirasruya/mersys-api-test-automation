package api.tests;

import api.base.BaseTest;
import api.endpoints.CustomFieldEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class US104_CustomFieldTest extends BaseTest {

    private CustomFieldEndpoints endpoints;
    private String fieldId;
    private String commonName;
    private final String TENANT_ID = "5e39ade1cb4c066deeb43015";

    @BeforeClass
    public void setupTest() {
        endpoints = new CustomFieldEndpoints();
        commonName = "SDET_Field_Automation_" + (int)(Math.random() * 10000);
        LOGGER.info("Starting US104: Custom Field Lifecycle Test");
        LOGGER.info("Generated Test Data - Name: {}", commonName);
    }

    @Test(priority = 1, description = "Create a new Custom Field with valid data")
    public void createFieldPositiveTest() {
        LOGGER.info("STEP 1: Creating a new custom field...");

        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("name", commonName);
        reqBody.put("type", "STRING");
        reqBody.put("tenantId", TENANT_ID);
        reqBody.put("maxLength", 100);
        reqBody.put("rows", 1);
        reqBody.put("multiple", false);
        reqBody.put("systemField", false);
        reqBody.put("constant", false);

        Response response = endpoints.createField(reqBody);

        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201 was not returned.");

        fieldId = response.path("id");
        LOGGER.info("Field successfully created with ID: {}", fieldId);
        Assert.assertNotNull(fieldId, "Resource ID should not be null after creation.");
    }

    @Test(priority = 2, dependsOnMethods = "createFieldPositiveTest", description = "Negative: Ensure duplicate names are not allowed")
    public void createFieldDuplicateNegativeTest() {
        LOGGER.info("STEP 2: Attempting to create a duplicate field with name: {}", commonName);

        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("name", commonName);
        reqBody.put("type", "STRING");
        reqBody.put("tenantId", TENANT_ID);

        Response response = endpoints.createField(reqBody);

        if (response.getStatusCode() == 201) {
            LOGGER.error("CRITICAL BUG: System allowed creating a duplicate field name!");
        }

        Assert.assertEquals(response.getStatusCode(), 400, "Business logic violation: Duplicate names should return 400 Bad Request.");
        LOGGER.info("Negative test passed: System correctly rejected duplicate name with status 400.");
    }

    @Test(priority = 3, dependsOnMethods = "createFieldPositiveTest", description = "Update an existing Custom Field")
    public void updateFieldPositiveTest() {
        LOGGER.info("STEP 3: Updating custom field with ID: {}", fieldId);

        Map<String, Object> updateBody = new HashMap<>();
        updateBody.put("id", fieldId);
        updateBody.put("name", commonName + "_Updated");
        updateBody.put("type", "STRING");
        updateBody.put("tenantId", TENANT_ID);
        updateBody.put("maxLength", 255);

        Response response = endpoints.updateField(updateBody);

        Assert.assertEquals(response.getStatusCode(), 200, "Update request failed.");
        LOGGER.info("Field successfully updated. Status: {}", response.getStatusCode());
    }

    @Test(priority = 4, dependsOnMethods = "updateFieldPositiveTest", description = "Negative: Update with existing data check")
    public void updateFieldDuplicateNegativeTest() {
        LOGGER.info("STEP 4: Testing duplicate constraint on Update for ID: {}", fieldId);

        Map<String, Object> updateBody = new HashMap<>();
        updateBody.put("id", fieldId);
        updateBody.put("name", commonName + "_Updated"); // Already updated to this name
        updateBody.put("tenantId", TENANT_ID);

        Response response = endpoints.updateField(updateBody);

        Assert.assertEquals(response.getStatusCode(), 400, "Should not allow update with identical existing data.");
        LOGGER.info("Update constraint validated successfully.");
    }

    @Test(priority = 5, alwaysRun = true, description = "Cleanup: Delete created field")
    public void deleteFieldTest() {
        if (fieldId != null) {
            LOGGER.info("STEP 5: Cleaning up. Deleting field ID: {}", fieldId);

            Response response = endpoints.deleteField(fieldId);

            int status = response.getStatusCode();
            Assert.assertTrue(status == 200 || status == 204, "Deletion failed. Status: " + status);
            LOGGER.info("Cleanup completed successfully. Resource removed.");
        } else {
            LOGGER.warn("Cleanup skipped: fieldId is null. Create test might have failed.");
        }
    }
}