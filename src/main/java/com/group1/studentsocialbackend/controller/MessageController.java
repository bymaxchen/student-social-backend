package com.group1.studentsocialbackend.controller;


import com.group1.studentsocialbackend.PO.Message;
import com.group1.studentsocialbackend.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Resource
    private MessageService messageService;
    // Create a new post
    @GetMapping("/chathistory")
    public ResponseEntity<List<Message>> showHistory(@RequestParam("sendId") String sendId, @RequestParam("acceptId") String acceptId) {
        return new ResponseEntity<>(messageService.chatHistory(sendId,acceptId), HttpStatus.OK);
    }
    
}
