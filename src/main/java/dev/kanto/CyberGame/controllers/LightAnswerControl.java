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
    @CrossOrigin(origins = "http://localhost:3001/")
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
                .header("Authorization", "Bearer c580ede58c2fcc3e90333fe14f0d12957322d1c5f1d677c5abace17b809ce8fb")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(obj))
                .exchange()
                .block();
        System.out.println(responseSpec);
        return "Light on";
    }
}
