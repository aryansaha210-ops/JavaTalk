package com.aryan.javatalk.ui;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {

    public ChatPanel() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(43,43,43));
        setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

    }

    public void addMessage(String sender, String message){

        MessageBubble bubble = new MessageBubble(sender,message);

        bubble.setAlignmentX(
                sender.equals("You")
                        ? Component.RIGHT_ALIGNMENT
                        : Component.LEFT_ALIGNMENT);

        add(bubble);
        add(Box.createVerticalStrut(10));

        revalidate();
        repaint();

        SwingUtilities.invokeLater(() -> scrollRectToVisible(getBounds()));
    }
    public String getChatText() {

        StringBuilder builder = new StringBuilder();

        for (Component component : getComponents()) {

            if (component instanceof MessageBubble bubble) {

                builder.append(bubble.getMessageText());
                builder.append("\n\n");

            }

        }

        return builder.toString();
    }

}