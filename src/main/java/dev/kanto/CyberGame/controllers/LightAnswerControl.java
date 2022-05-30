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
    @CrossOrigin(origins = "http://65.21.234.182:3000")
    public String LightQuestion(@RequestParam String id) {
        Answer answer = AnswerRepository.findAnswerByid(id);

        if(!Objects.equals(answer.getLightURL(), "null")) {
            System.out.println("Lights Engaged - Answer");
            return CreateWebClient(answer.getLightURL());
        }
        return null;
    }
    private String CreateWebClient(String URL) {
        JSONObject obj = new JSONObject();
        obj.put("cycles", 3);
        obj.put("period", 2);
        obj.put("color", "green");
        WebClient client = WebClient.create();
        ClientResponse responseSpec = client.post()
                .uri(URL)
                .header("Authorization", "Bearer cd37b758938e9e0cdaffec6be56a6fb9c894480cac9cb341b5f55b51efc937ca")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(obj))
                .exchange()
                .block();
        return null;
    }
}
