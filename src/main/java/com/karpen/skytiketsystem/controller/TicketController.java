package com.karpen.skytiketsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karpen.skytiketsystem.Data;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public String applicationGet(){
        return "404 Not found";
    }

    @GetMapping("/all")
    public ResponseEntity<Resource> getFile(){
        File file = new File("application.json");

        if (!file.exists()){
            return ResponseEntity.notFound().build();
        }

        Resource resource = new FileSystemResource(file);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
    }

    private void saveApplicationsToFile(){
        try {
            objectMapper.writeValue(new File(filepath), applications);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
