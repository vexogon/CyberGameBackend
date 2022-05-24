package dev.kanto.CyberGame.controllers;

import dev.kanto.CyberGame.Repositories.AnswerRepository;
import dev.kanto.CyberGame.model.Answer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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
    @CrossOrigin(origins = "http://192.168.240.3:3001")
    public String LightQuestion(@RequestParam String id) {
        Answer answer = AnswerRepository.findAnswerByid(id);

        if(!Objects.equals(answer.getLightURL(), "null")) {
            return CreateWebClient(answer.getLightURL());

        }
        return null;
    }
    private String CreateWebClient(String URL) {
        WebClient client = WebClient.create();
        String Colour = """
                cycles=5
                period=2
                color=green""";
        WebClient.ResponseSpec responseSpec = client.post()
                .uri(URL)
                .header("Authorization", "Bearer: cf2043521b1afba29bb87603bc695ea519722101cf8c3e597d41779a4a430ae0")
                .contentType(MediaType.TEXT_PLAIN)
                .bodyValue(Colour)
                .retrieve();
        String responseBody = responseSpec.bodyToMono(String.class).block();
        return responseBody;
    }
}
