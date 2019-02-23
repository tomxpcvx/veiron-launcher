package de.veiron.launcher.screens;

import de.veiron.launcher.manager.CredentialsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartScreen extends JFrame implements ActionListener {

    CredentialsManager cm = new CredentialsManager();

    JButton b_play;
    JLabel l_alert;

    public StartScreen() {

        // All elements
        JButton b_play = new JButton("Play!");
        JLabel l_alert = new JLabel("Willkommen, " + cm.getIngameName() + "!");

        this.setLayout(null);

        JEditorPane browser = new JEditorPane();
        browser.setEditable(false);

        try {
            browser.setPage("https://veiron.tomtx.xyz/assets/launcher/changelog");
        } catch (IOException e) {
            e.printStackTrace();
            browser.setContentType("text/html");
            browser.setText("<html>Could not load.<br>Please check your internet connection!<br><br>Try to restart the launcher.</html>");
            b_play.setEnabled(false);
        }


        Font boldFont = new Font(b_play.getFont().getName(), Font.BOLD, 20);
        b_play.setFont(boldFont);
        b_play.setEnabled(false);

        // Register locations of elements
        l_alert.setBounds(120, 460, 350, 20);
        b_play.setBounds(50, 495, 290, 65);
        browser.setBounds(-3,-3,400,455);

        // Register Action Listener for button
        b_play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Add all elements in JFrame
        this.add(l_alert);
        this.add(b_play);
        this.add(browser);

        // Set Preferences for JFrame
        this.setTitle("Veiron Launcher");
        this.setSize(400, 626);
        this.setResizable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
