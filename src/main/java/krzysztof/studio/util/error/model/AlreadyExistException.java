package krzysztof.studio.util.error.model;

public class AlreadyExistException extends Exception{

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}