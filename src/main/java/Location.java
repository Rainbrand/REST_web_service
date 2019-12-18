public class Location {
    private String id;
    private String description;
    private String type;

    public Location(){ }

    public Location(String id, String description, String type) {
        this.id = id;
        this.description = description;
        this.type = type;
    }

    public String getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Location [id =" + id  + ", description =" + description + ", type =" + type + "]";
    }
}