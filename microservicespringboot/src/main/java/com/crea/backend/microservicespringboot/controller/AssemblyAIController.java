package com.crea.backend.microservicespringboot.controller;

import java.util.Map;

// import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crea.backend.microservicespringboot.services.AssemblyAiService;

@RestController
public class AssemblyAIController {

    @Autowired
    private AssemblyAiService assemblyAIService;

    @PostMapping("/transcribe")
    public ResponseEntity<?> transcribeAudio(@RequestBody Map<String, String> requestBody) {
        String audioUrl = requestBody.get("audio_url");

        if (audioUrl == null) {
            return ResponseEntity.badRequest().body("Missing audio_url in the request");
        }

        String transcription = assemblyAIService.transcribeAudio(audioUrl);
        if (transcription != null) {
            return ResponseEntity.ok(transcription);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Transcription request failed");
        }
    }

     @GetMapping("/transcriptions")
    public ResponseEntity<?> getAllTranscriptions() {
        String transcriptions = assemblyAIService.getAllTranscriptions();
        
        if (transcriptions != null) {
            return ResponseEntity.ok(transcriptions);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve transcriptions");
        }
    }

     @GetMapping("/transcript/{transcriptionId}")
    public ResponseEntity<?> getTranscriptionById(@PathVariable String transcriptionId) {
        String transcription = assemblyAIService.getTranscriptionById(transcriptionId);
        if (transcription != null) {
            return ResponseEntity.ok(transcription);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve transcription by ID");
        }
    }
}

