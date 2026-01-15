package api.models.response;

public class CustomFieldResponse {
    private String id;
    private String name;
    private String type;
    private String orderNo;
    private String revisionId;
    private String createdDate;
    private String changedDate;
    private boolean systemField;

    // Default Constructor
    public CustomFieldResponse() {}

    // GETTER & SETTER
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getOrderNo() { return orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }

    public String getRevisionId() { return revisionId; }
    public void setRevisionId(String revisionId) { this.revisionId = revisionId; }

    public String getCreatedDate() { return createdDate; }
    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }

    public String getChangedDate() { return changedDate; }
    public void setChangedDate(String changedDate) { this.changedDate = changedDate; }

    public boolean isSystemField() { return systemField; }
    public void setSystemField(boolean systemField) { this.systemField = systemField; }
}
