package com.agrofarm.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@Service
public class ChatService {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    public String askOpenAI(String userMessage, String polygonId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", List.of(
                Map.of("role", "system", "content", "Ты — ИИ агропомощник. Отвечай кратко, чётко и по делу. Пользователь спрашивает о сельском хозяйстве."),
                Map.of("role", "user", "content", userMessage + (polygonId != null ? " (Полигон: " + polygonId + ")" : ""))
        ));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(OPENAI_API_URL, request, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(response.getBody());
            return json
                    .path("choices").get(0)
                    .path("message")
                    .path("content").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Произошла ошибка при обращении к OpenAI.";
        }
    }
}
