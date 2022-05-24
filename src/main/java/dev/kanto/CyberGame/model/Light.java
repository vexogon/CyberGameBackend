package dev.kanto.CyberGame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "Database")
public class Light {
    @Id
    private String id;
    private String Lightset;
    private int triggerOne;

    public String getLifeOneURL() {
        return lifeOneURL;
    }

    public void setLifeOneURL(String lifeOneURL) {
        this.lifeOneURL = lifeOneURL;
    }

    public String getLifeXTwoURL() {
        return lifeXTwoURL;
    }

    public void setLifeXTwoURL(String lifeXTwoURL) {
        this.lifeXTwoURL = lifeXTwoURL;
    }

    private int triggerTwo;
    private String activation;
    private String authKey;
    private String lifeOneURL;
    private String lifeXTwoURL;

    public String getLightset() {
        return Lightset;
    }

    public void setLightset(String lightset) {
        Lightset = lightset;
    }

    public int getTriggerOne() {
        return triggerOne;
    }

    public void setTriggerOne(int triggerOne) {
        this.triggerOne = triggerOne;
    }

    public int getTriggerTwo() {
        return triggerTwo;
    }

    public void setTriggerTwo(int triggerTwo) {
        this.triggerTwo = triggerTwo;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getId() {
        return id;
    }
}

