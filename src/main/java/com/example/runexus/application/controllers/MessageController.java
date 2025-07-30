package com.example.runexus.application.controllers;

import com.example.runexus.application.dto.MessageInput;
import com.example.runexus.domain.models.Message;
import com.example.runexus.domain.ports.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // 1. Yeni mesaj gönder
    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody MessageInput input) {
        Message savedMessage = messageService.sendMessage(input);
        return ResponseEntity.ok(savedMessage);
    }

    // 2. İki kullanıcı arasındaki mesajları getir
    @GetMapping("/{senderId}/{receiverId}")
    public ResponseEntity<List<Message>> getConversation(
            @PathVariable Long senderId,
            @PathVariable Long receiverId
    ) {
        List<Message> messages = messageService.getMessagesBetweenUsers(senderId, receiverId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/has-new/{userId}")
    public ResponseEntity<Boolean> hasNewMessages(@PathVariable Long userId) {
        boolean hasNew = messageService.hasUnreadMessages(userId);
        return ResponseEntity.ok(hasNew);
    }

}

