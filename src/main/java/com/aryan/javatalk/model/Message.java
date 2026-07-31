package com.aryan.javatalk.model;

import java.time.LocalDateTime;

public class Message {

    private String sender;
    private String text;
    private LocalDateTime time;

    public Message(String sender, String text) {
        this.sender = sender;
        this.text = text;
        this.time = LocalDateTime.now();
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTime() {
        return time;
    }
}