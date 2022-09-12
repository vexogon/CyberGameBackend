package dev.kanto.CyberGame.controllers;

import dev.kanto.CyberGame.Repositories.LightRepository;
import dev.kanto.CyberGame.model.Light;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class LightProgressControl {
    private final LightRepository LightRepository;

    public LightProgressControl(dev.kanto.CyberGame.Repositories.LightRepository lightRepository) {
        LightRepository = lightRepository;

    }


    @GetMapping("/LightByProgress/")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3001/")
    public String getChangeLight(@RequestParam String id, @RequestParam int tp) {
        Light Light = LightRepository.findLightByid(id);
        if (Light.getTriggerOne() == tp) {
            return CreateWebClient(Light, Light.getLifeOneURL());

        } else if (Light.getTriggerTwo() == tp) {
            return CreateWebClient(Light, Light.getLifeXTwoURL());
        }
        return "You haven't reached a light trigger point";
    }

    private String CreateWebClient(Light light, String URL) {
        WebClient client = WebClient.create();
        String Colour = "color=blue";
        WebClient.ResponseSpec responseSpec = client.put()
                .uri(URL)
                .contentType(MediaType.TEXT_PLAIN)
                .bodyValue(Colour)
                .retrieve();
        String responseBody = responseSpec.bodyToMono(String.class).block();
        return responseBody;
    }

    @PostMapping("/postLight/")
    @CrossOrigin(origins = "http://localhost:3001/")
    public Light CreateQuestion(@RequestBody Light light) {
        Light saved = LightRepository.save(light);
        return saved;
    }
}
