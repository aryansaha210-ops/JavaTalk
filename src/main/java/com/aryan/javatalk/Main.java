package com.aryan.javatalk;

import javax.swing.SwingUtilities;
import com.formdev.flatlaf.FlatDarkLaf;
import com.aryan.javatalk.ui.ChatWindow;

public class Main {

    public static void main(String[] args) {

        FlatDarkLaf.setup();

        SwingUtilities.invokeLater(() -> {
            new ChatWindow().setVisible(true);
        });
    }
}
