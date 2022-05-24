package dev.kanto.CyberGame.controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dev.kanto.CyberGame.Repositories.AnswerRepository;
import dev.kanto.CyberGame.Repositories.QuestionRepository;
import dev.kanto.CyberGame.model.Answer;
import dev.kanto.CyberGame.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @CrossOrigin(origins = "http://192.168.240.3:3001")
    public Question GetQuestion(@RequestParam String id) {
        System.out.println("Getting Question by id: " + id);
        Question question = Questionrepository.findQuestionByid(id);
        System.out.println(question.getLfc());
        if(!Objects.equals(question.getLfc(), "null")) {
            CreateWebClient(question.getLfc());
        }
        return question;
    }
    private String CreateWebClient(String color) {
        String request;
        WebClient client = WebClient.create();
        if(color == "red") {
             request = """
                cycles=5
                period=2
                color="red""";

        }
        else {
             request = """
                    cycles=5
                    period=2
                    color="green""";
        }
        try {
            WebClient.ResponseSpec responseSpec = client.post()
                    .uri("https://api.lifx.com/v1/lights/all/effects/pulse")
                    .header("Authorization", "Bearer: cf2043521b1afba29bb87603bc695ea519722101cf8c3e597d41779a4a430ae0")
                    .contentType(MediaType.TEXT_PLAIN)
                    .bodyValue(request)
                    .retrieve();
            String responseBody = responseSpec.bodyToMono(String.class).block();
            return responseBody;
        }
        catch(Exception e) {
            return null;
        }
    }

    @PostMapping("/CreateQuestion/")
    @CrossOrigin(origins = "http://192.168.240.3:3001")
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
