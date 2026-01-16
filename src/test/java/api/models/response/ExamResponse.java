package api.models.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExamResponse {
    private String id;
    private String name;

    public ExamResponse() {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GradeLevel {
        private String id;
        private String name;

        public GradeLevel() {}

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


}
