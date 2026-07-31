package com.aryan.javatalk.util;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    public static void saveChat(JFrame parent, String chatText) {

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Chat");

        int result = chooser.showSaveDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {

            File file = chooser.getSelectedFile();

            if (!file.getName().endsWith(".txt")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }

            try (FileWriter writer = new FileWriter(file)) {

                writer.write(chatText);

                JOptionPane.showMessageDialog(parent,
                        "Chat saved successfully!");

            } catch (IOException e) {

                JOptionPane.showMessageDialog(parent,
                        "Error: " + e.getMessage());
            }
        }
    }
}