package api.endpoints;

import api.base.BaseTest;

public class EduStandartsEndpoints extends BaseTest {
    private static final String BASE_PATH = "/school-service/api/education-standard";

    public static final String CREATE = BASE_PATH;
    public static final String UPDATE = BASE_PATH;
    public static final String DELETE = BASE_PATH + "/{id}";
    public static final String LIST   = BASE_PATH + "/school/{schoolId}";

}
