package com.agrofarm.backend.controller;

import com.agrofarm.backend.dto.ChatRequest;
import com.agrofarm.backend.dto.ChatResponse;
import com.agrofarm.backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String reply = chatService.askOpenAI(request.getMessage(), request.getPolygonId());
        return new ChatResponse(reply);
    }
}
