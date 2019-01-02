package pl.rafalpieniazek.newword.model;

/**
 * Created by rafalpieniazek on 25/09/2018.
 */

public class FlashCard {
    private String keyword;
    private String description;

    public FlashCard(String keyword, String description) {
        this.keyword = keyword;
        this.description = description;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
