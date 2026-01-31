package api.models.request;

public class StudentGroupRequest {

    private String id;
    private String name;
    private String description;
    private boolean active;
    private boolean publicGroup;
    private boolean visibility;

    public StudentGroupRequest() {}

    public StudentGroupRequest(String name, String description, boolean active, boolean publicGroup, boolean visibility) {
        this.name = name;
        this.description = description;
        this.active = active;
        this.publicGroup = publicGroup;
        this.visibility = visibility;
    }

    // getter-setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public boolean isPublicGroup() { return publicGroup; }
    public void setPublicGroup(boolean publicGroup) { this.publicGroup = publicGroup; }

    public boolean isVisibility() { return visibility; }
    public void setVisibility(boolean visibility) { this.visibility = visibility; }
}
