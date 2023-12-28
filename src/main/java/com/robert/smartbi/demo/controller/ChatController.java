package com.robert.smartbi.demo.controller;

import com.robert.smartbi.demo.model.entity.Chat;
import com.robert.smartbi.demo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/{id}")
    public Chat getChatById(@PathVariable Long id) {
        return chatService.getById(id);
    }

    @GetMapping
    public List<Chat> getAllChats() {
        return chatService.list();
    }

    @PostMapping
    public void createChat(@RequestBody Chat chat) {
        chatService.save(chat);
    }

    @PutMapping("/{id}")
    public void updateChat(@PathVariable Long id, @RequestBody Chat chat) {
        chat.setId(id);
        chatService.updateById(chat);
    }

    @DeleteMapping("/{id}")
    public void deleteChatById(@PathVariable Long id) {
        chatService.removeById(id);
    }
}
