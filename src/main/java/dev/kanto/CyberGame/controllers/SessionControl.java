package dev.kanto.CyberGame.controllers;

import dev.kanto.CyberGame.Repositories.PlayerRepository;
import dev.kanto.CyberGame.model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
public class SessionControl {
    private PlayerRepository playerRepository;

    public SessionControl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/SessionStart/")
    @CrossOrigin(origins = "http://65.21.234.182:3001")
    public ResponseEntity Timing(HttpSession session) {
        LocalDateTime date = LocalDateTime.now();
        int seconds = date.toLocalTime().toSecondOfDay();
        System.out.println(seconds);

        Object  Time = session.getAttribute("Time");
        Object endTime = session.getAttribute("Time");


        Time = seconds;
        endTime = seconds;


        session.setAttribute("Time", Time);
        session.setAttribute("endTime", endTime);
        System.out.println("Below");
        return ResponseEntity.ok(Time);
    }

    @PostMapping("/SessionStop/")
    @CrossOrigin(origins = "http://65.21.234.182:3001")
    public Player SessionStop(HttpSession session, @RequestParam String username) {

        LocalDateTime date = LocalDateTime.now();
        int seconds = date.toLocalTime().toSecondOfDay();
        System.out.println(seconds);


        session.setAttribute("endTime", seconds);
        int Time = (int) session.getAttribute("Time");
        int endTime = (int) session.getAttribute("endTime");
        System.out.println(endTime);
        System.out.println(Time);
        int TimeTaken;

        long completedTime = endTime - Time;
        System.out.println(completedTime);

        Player player = new Player(username, completedTime);
//        System.out.println("here");
//        player.setTime(completedTime);
//        System.out.println("her2");
//        player.setUsername(username);
//        System.out.println("here3");
        playerRepository.save(player);

        return player;
    }
}
