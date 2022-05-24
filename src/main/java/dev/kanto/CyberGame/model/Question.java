package dev.kanto.CyberGame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "Database")
public class Question {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    private String text;

    public String getLfc() {
        return lfc;
    }

    public void setLfc(String lfc) {
        this.lfc = lfc;
    }

    private String lfc;
    private List<String> AnswerID;

    public List<String> getAnswerID() {
        return AnswerID;
    }

    public void setAnswerID(List<String> answerID) {
        AnswerID = answerID;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    private List<Answer> answers;

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
