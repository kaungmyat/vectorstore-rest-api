package com.example.vectorstore.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vectorstore.data.PCFilter;
import com.example.vectorstore.services.PineconeService;


@RestController
@RequestMapping("/pinecone")
public class PineconeController {

    private final PineconeService pineconeService;

    public PineconeController(PineconeService pineconeService) {
        this.pineconeService = pineconeService;
    }

    @GetMapping("/welcome")
    public String showWelcomeScreen() {
        return "Welcome to Vectorstore!!!";
    }
    
    
    @PostMapping(value = "/get-inquiry")
    public ResponseEntity<String> getInquiry(@RequestBody PCFilter pCFilter) {
        String results = pineconeService.getAnswer(pCFilter);

        return ResponseEntity.ok(results);
    }
}
