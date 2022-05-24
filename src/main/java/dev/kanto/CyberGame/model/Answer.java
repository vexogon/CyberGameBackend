package dev.kanto.CyberGame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "Database")
public class Answer {

    @Id
    private String id;

    private String text;

    private String LightURL;

    public String getLightURL() {
        return LightURL;
    }

    public void setLightURL(String lightURL) {
        LightURL = lightURL;
    }

    public String getLinkedQuestionID() {
        return LinkedQuestionID;
    }

    private String LinkedQuestionID;

    public String getSeconderyQuestionID() {
        return SeconderyQuestionID;
    }

    public void setSeconderyQuestionID(String seconderyQuestionID) {
        SeconderyQuestionID = seconderyQuestionID;
    }

    private String SeconderyQuestionID;

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getQLinkedQuestionID() {
        return this.LinkedQuestionID;
    }

    public void setLinkedQuestionID(String questionID) {
        this.LinkedQuestionID = questionID;
    }
}
