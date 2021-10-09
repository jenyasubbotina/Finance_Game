package bank.hackaton.model;

public class CaseModel {
    private String name;
    private String description;
    private String imageUrl;
    private Boolean answer;

    public CaseModel(String name, String description, String imageUrl, Boolean answer) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.answer = answer;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean getAnswer() {
        return answer;
    }
}
