package dev.kanto.CyberGame.controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dev.kanto.CyberGame.Repositories.AnswerRepository;
import dev.kanto.CyberGame.Repositories.QuestionRepository;
import dev.kanto.CyberGame.model.Answer;
import dev.kanto.CyberGame.model.Question;
import net.minidev.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
public class QuestionControl {
    private final QuestionRepository Questionrepository;
    private final AnswerRepository AnswerRepository;

    public QuestionControl(QuestionRepository Questionrepository, dev.kanto.CyberGame.Repositories.AnswerRepository answerRepository) {
        this.Questionrepository = Questionrepository;
        this.AnswerRepository = answerRepository;
    }
    @GetMapping("/GetQuestion/")
    @ResponseBody
    @CrossOrigin(origins = "http://13.40.117.145:3000/")
    public Question GetQuestion(@RequestParam String id) {
        System.out.println("Getting Question by id: " + id);
        Question question = Questionrepository.findQuestionByid(id);
        System.out.println(question.getLfc());
        if(!Objects.equals(question.getLfc(), "null")) {
            CreateWebClient(question.getLfc());
            return question;
        }
        return question;

    }
    private Exception CreateWebClient(String color) {
        String request;
        WebClient client = WebClient.create();
        JSONObject obj = new JSONObject();
        obj.put("cycles", 5);
        obj.put("period", 0.8);

        if(Objects.equals(color, "red")) {
            obj.put("color", "red");


        }
        else {
            obj.put("color", "green");
        }
        try {

            ClientResponse responseSpec = client.post()
                    .uri("https://api.lifx.com/v1/lights/all/effects/pulse")
                    .header("Authorization", "Bearer c840946f1b4728f0e9efba9f2cb0c7738965bfe475b59d8015940df8a1956495")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(obj))
                    .exchange()
                    .block();
            return null;
        }
        catch(Exception e) {
            return e;
        }
    }


    @PostMapping("/CreateQuestion/")
    @CrossOrigin(origins = "http://13.40.117.145:3000/")
    public ResponseEntity CreateQuestion(@RequestBody Question question) {
         List<String> answerIds = new ArrayList<>();
        try {
            Questionrepository.save(question);
            List<Answer> answers = question.getAnswers();
            for (int i = 0; i <= answers.size(); i++) {
                Answer answer = answers.get(i);
                AnswerRepository.save(answer);
                answerIds.add(answer.getId());
                question.setAnswerID(answerIds);
                Questionrepository.save(question);

            }
            return null;
        }
        catch (Exception e) {
            return null;
        }

    }
}
