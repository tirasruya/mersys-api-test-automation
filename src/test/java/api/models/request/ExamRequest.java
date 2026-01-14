package api.models.request;

public class ExamRequest {
    private String id;
    private String name;
    private String registrationStartDate;
    private String registrationEndDate;
    private String type = "REGISTRATION";
    private String school = "695c16bdc138c05a387fe36f";
    private String academicPeriod = "695c1c03f620a8876fd6e521";
    private GradeLevel gradeLevel;


    public ExamRequest() {}


    public static class GradeLevel {
        private String id;

        public GradeLevel() {}
        public GradeLevel(String id) { this.id = id; }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
    }

    // Getter ve Setter Metodları
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRegistrationStartDate() { return registrationStartDate; }
    public void setRegistrationStartDate(String registrationStartDate) { this.registrationStartDate = registrationStartDate; }

    public String getRegistrationEndDate() { return registrationEndDate; }
    public void setRegistrationEndDate(String registrationEndDate) { this.registrationEndDate = registrationEndDate; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSchool() { return school; }
    public void setSchool(String school) { this.school = school; }

    public String getAcademicPeriod() { return academicPeriod; }
    public void setAcademicPeriod(String academicPeriod) { this.academicPeriod = academicPeriod; }

    public GradeLevel getGradeLevel() { return gradeLevel; }
    public void setGradeLevel(GradeLevel gradeLevel) { this.gradeLevel = gradeLevel; }
}