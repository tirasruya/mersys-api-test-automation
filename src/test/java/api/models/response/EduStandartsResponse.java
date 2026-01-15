package api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class EduStandartsResponse {
    private String id;
    private String name;
    private String description;
    private String schoolId;
    private String gradeLevelId;
    private String subjectId;

    // Default Constructor
    public EduStandartsResponse() {
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSchoolId() { return schoolId; }
    public void setSchoolId(String schoolId) { this.schoolId = schoolId; }

    public String getGradeLevelId() { return gradeLevelId; }
    public void setGradeLevelId(String gradeLevelId) { this.gradeLevelId = gradeLevelId; }

    public String getSubjectId() { return subjectId; }
    public void setSubjectId(String subjectId) { this.subjectId = subjectId; }
}
