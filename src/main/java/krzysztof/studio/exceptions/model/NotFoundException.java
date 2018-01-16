package krzysztof.studio.exceptions.model;

public class NotFoundException extends Exception {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}