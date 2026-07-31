package com.aryan.javatalk.ui;

import com.aryan.javatalk.ai.AIService;
import com.aryan.javatalk.util.FileManager;
import javax.swing.*;
import java.awt.*;

public class ChatWindow extends JFrame {

    private ChatPanel chatPanel;
    private JTextField inputField;
    private JButton sendButton;

    public ChatWindow() {

        setTitle("☕ JavaTalk");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ==========================
        // Menu Bar
        // ==========================
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem newChat = new JMenuItem("New Chat");
        JMenuItem saveChat = new JMenuItem("Save Chat");
        JMenuItem exit = new JMenuItem("Exit");

        fileMenu.add(newChat);
        fileMenu.add(saveChat);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        JMenu helpMenu = new JMenu("Help");

        JMenuItem about = new JMenuItem("About");

        helpMenu.add(about);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        // ==========================
        // Header
        // ==========================
        JLabel title = new JLabel("☕ JavaTalk", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        add(title, BorderLayout.NORTH);

        // ==========================
        // Chat Panel
        // ==========================
        chatPanel = new ChatPanel();

        JScrollPane scrollPane = new JScrollPane(chatPanel);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);

        // ==========================
        // Bottom Panel
        // ==========================
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));

        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        sendButton = new JButton("Send");

        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        // ==========================
        // Welcome Messages
        // ==========================
        chatPanel.addMessage("JavaTalk", "👋 Hello!");
        chatPanel.addMessage("JavaTalk", "I'm your AI assistant.");
        chatPanel.addMessage("JavaTalk", "Ask me anything.");

        // ==========================
        // Button Actions
        // ==========================
        sendButton.addActionListener(e -> sendMessage());

        inputField.addActionListener(e -> sendMessage());

        newChat.addActionListener(e -> {

            dispose();

            new ChatWindow().setVisible(true);

        });

        about.addActionListener(e ->

                JOptionPane.showMessageDialog(
                        this,
                        "☕ JavaTalk\n\nVersion 1.0\nBuilt with Java 23\nPowered by OpenRouter AI",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE)

        );

        exit.addActionListener(e -> System.exit(0));
        saveChat.addActionListener(e ->
                FileManager.saveChat(this, chatPanel.getChatText())
        );

    }

    private void sendMessage() {

        String message = inputField.getText().trim();

        if (message.isEmpty()) {
            return;
        }

        chatPanel.addMessage("You", message);

        inputField.setText("");

        sendButton.setEnabled(false);

        chatPanel.addMessage("JavaTalk", "Typing...");

        SwingWorker<String, Void> worker = new SwingWorker<>() {

            @Override
            protected String doInBackground() {

                return AIService.askAI(message);

            }

            @Override
            protected void done() {

                try {

                    String reply = get();

                    chatPanel.remove(chatPanel.getComponentCount() - 2);
                    chatPanel.remove(chatPanel.getComponentCount() - 1);

                    chatPanel.revalidate();
                    chatPanel.repaint();

                    chatPanel.addMessage("JavaTalk", reply);

                } catch (Exception e) {

                    chatPanel.addMessage("JavaTalk",
                            "Error: " + e.getMessage());

                }

                sendButton.setEnabled(true);

            }

        };

        worker.execute();

    }

}