package com.aryan.javatalk.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import com.aryan.javatalk.util.TimeUtil;

public class MessageBubble extends JPanel {
    private final String messageText;

    public MessageBubble(String sender, String message) {
        this.messageText = sender + ": " + message;

        setOpaque(false);

        setLayout(new FlowLayout(
                sender.equals("You") ? FlowLayout.RIGHT : FlowLayout.LEFT));

        JPanel bubblePanel = new JPanel();
        bubblePanel.setLayout(new BorderLayout());
        bubblePanel.setOpaque(false);

        JTextArea text = new JTextArea(message);

        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        text.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        text.setBorder(new EmptyBorder(10, 15, 10, 15));

        text.setBackground(
                sender.equals("You")
                        ? new Color(0, 120, 215)
                        : new Color(70, 70, 70));

        text.setForeground(Color.WHITE);
        JLabel timeLabel = new JLabel(TimeUtil.getCurrentTime());

        timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        timeLabel.setForeground(Color.LIGHT_GRAY);

        bubblePanel.add(text, BorderLayout.CENTER);
        bubblePanel.add(timeLabel, BorderLayout.SOUTH);

        add(bubblePanel);
    }
    public String getMessageText() {
        return messageText;
    }
}