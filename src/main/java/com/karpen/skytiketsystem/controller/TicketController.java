package com.karpen.skytiketsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karpen.skytiketsystem.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/application")
public class TicketController {

    private final List<Data> applications = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String filepath = "application.json";

    @PostMapping
    public ResponseEntity<String> submitApplication(@RequestBody Data application) {
        applications.add(application);
        saveApplicationsToFile();
        return ResponseEntity.ok("Application submitted successfully.");
    }

    private void saveApplicationsToFile(){
        try {
            objectMapper.writeValue(new File(filepath), applications);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
