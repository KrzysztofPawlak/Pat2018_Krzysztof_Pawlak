package krzysztof.studio.util.error.model;

public class NotFoundException extends Exception {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}