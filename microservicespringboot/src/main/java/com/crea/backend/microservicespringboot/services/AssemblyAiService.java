    package com.crea.backend.microservicespringboot.services;

    // import org.springframework.beans.factory.annotation.Value;
    import org.springframework.http.*;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;
@Service
public class AssemblyAiService {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public AssemblyAiService() {
        this.restTemplate = new RestTemplate();
        
        // Set your API key in the request headers
        headers = new HttpHeaders();
        headers.set("authorization", "a2be6dbb6cf142bf8b24399a026ce3a1");
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public String transcribeAudio(String audioUrl) {
        String requestBodyJson = "{\"audio_url\": \"" + audioUrl + "\"}";

        HttpEntity<String> entity = new HttpEntity<>(requestBodyJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            "https://api.assemblyai.com/v2/transcript",
            HttpMethod.POST,
            entity,
            String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }
    
    public String getAllTranscriptions() {
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
            "https://api.assemblyai.com/v2/transcript",
            HttpMethod.GET,
            entity,
            String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public String getTranscriptionById(String transcriptionId) {
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
            "https://api.assemblyai.com/v2/transcript/" + transcriptionId,
            HttpMethod.GET,
            entity,
            String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }
}