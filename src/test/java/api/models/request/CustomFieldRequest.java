package api.models.request;

public class CustomFieldRequest {
    private String id; // PUT için gerekli
    private String name;
    private String orderNo;
    private String columnSize = "1"; // Default değer
    private String type;
    private String schoolId;

    // Default Constructor (JSON Deserialization için gerekli)
    public CustomFieldRequest() {}

    // POST için Constructor
    public CustomFieldRequest(String name, String orderNo, String type, String schoolId) {
        this.name = name;
        this.orderNo = orderNo;
        this.type = type;
        this.schoolId = schoolId;
    }

    // GETTER & SETTER
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getOrderNo() { return orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }

    public String getColumnSize() { return columnSize; }
    public void setColumnSize(String columnSize) { this.columnSize = columnSize; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSchoolId() { return schoolId; }
    public void setSchoolId(String schoolId) { this.schoolId = schoolId; }
}
