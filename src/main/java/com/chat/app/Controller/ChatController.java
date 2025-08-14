package com.chat.app.Controller;

import com.chat.app.Model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    private final Set<String> connectedUsers = ConcurrentHashMap.newKeySet();

    // Serve the chat HTML page
    @GetMapping("/")
    public String index() {
        return "chat"; // This will serve chat.html from templates folder
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        chatMessage.setTimestamp(LocalDateTime.now());
        System.out.println("Message from " + chatMessage.getSender() + ": " + chatMessage.getContent());
        return chatMessage;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        String sessionId = event.getMessage().getHeaders().get("simpSessionId").toString();
        connectedUsers.add(sessionId);
        System.out.println("New connection: " + sessionId + ". Total users: " + connectedUsers.size());
        messagingTemplate.convertAndSend("/topic/usercount", connectedUsers.size());
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        connectedUsers.remove(event.getSessionId());
        System.out.println("Disconnection: " + event.getSessionId() + ". Total users: " + connectedUsers.size());
        messagingTemplate.convertAndSend("/topic/usercount", connectedUsers.size());
    }
}

