package dev.kanto.CyberGame.controllers;

import org.apache.tomcat.jni.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
@RestController
public class SessionControl {

    @GetMapping("/SessionStart/")
    public ResponseEntity Timing(HttpSession session) {
        LocalDateTime date = LocalDateTime.now();
        int seconds = date.toLocalTime().toSecondOfDay();
        System.out.println(seconds);

        Object Time =  session.getAttribute("Time");
        Object endTime = session.getAttribute("Time");


        if (Time == null) {
            Time = seconds;
            endTime = seconds;

        }

        session.setAttribute("Time", Time);
        session.setAttribute("endTime", endTime);

        return ResponseEntity.ok(Time);
    }

    @GetMapping("/SessionStop/")
    public  ResponseEntity SessionStop(HttpSession session) {

        LocalDateTime date = LocalDateTime.now();
        LocalTime localTime = LocalTime.now();
        int seconds = date.toLocalTime().toSecondOfDay();
        System.out.println(seconds);


        session.setAttribute("endTime", seconds);
        int time = (int) session.getAttribute("Time");
        int endTime = (int) session.getAttribute("endTime");
        int TimeTaken;

        long completedTime = endTime - time;
        return ResponseEntity.ok(completedTime);
    }
}
