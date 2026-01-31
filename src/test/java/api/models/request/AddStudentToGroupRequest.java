package api.models.request;

import java.util.List;

public class AddStudentToGroupRequest {
    private List<String> studentIds;

    public AddStudentToGroupRequest() {}

    public AddStudentToGroupRequest(List<String> studentIds) {
        this.studentIds = studentIds;
    }
    public List<String> getStudentIds() { return studentIds; }
    public void setStudentIds(List<String> studentIds) { this.studentIds = studentIds; }
}
