package dev.kanto.CyberGame.controllers;

import dev.kanto.CyberGame.Repositories.AnswerRepository;
import dev.kanto.CyberGame.model.Answer;
import net.minidev.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@RestController
public class LightAnswerControl {
    private final AnswerRepository AnswerRepository;


    public LightAnswerControl(dev.kanto.CyberGame.Repositories.AnswerRepository answerRepository) {
        AnswerRepository = answerRepository;
    }
    @GetMapping("/LightAnswer/")
    @ResponseBody
    @CrossOrigin(origins = "http://13.40.117.145:3000/")
    public String LightQuestion(@RequestParam String id) {
        Answer answer = AnswerRepository.findAnswerByid(id);
        System.out.println("Running");

        if(!Objects.equals(answer.getLightURL(), "null")) {
            System.out.println("Lights Engaged - Answer");
            System.out.println(answer.getLightURL());
            return CreateWebClient(answer.getLightURL());

        }
        return null;
    }
    private String CreateWebClient(String URL) {
        JSONObject obj = new JSONObject();
        obj.put("cycles", 5);
        obj.put("period", 0.8);
        obj.put("color", "green");
        WebClient client = WebClient.create();
        ClientResponse responseSpec = client.post()
                .uri(URL)
                .header("Authorization", "Bearer c840946f1b4728f0e9efba9f2cb0c7738965bfe475b59d8015940df8a1956495")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(obj))
                .exchange()
                .block();
        System.out.println(responseSpec);
        return "Light on";
    }
}
