package api.endpoints;

public class StudentGroupEndpoints {

    public static final String BASE_PATH = "/school-service/api/student-group";
    public static final String CREATE = BASE_PATH;              // POST
    public static final String UPDATE = BASE_PATH;              // PUT
    public static final String DELETE = BASE_PATH + "/{id}";    // DELETE
    public static final String LIST   = BASE_PATH;              // GET (eğer varsa)
}
