package de.veiron.launcher.screens;

import de.veiron.launcher.manager.CredentialsManager;

import javax.swing.*;
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
            browser.setPage("https://veiron.tomtx.xyz/launcher/changelog");
        } catch (IOException e) {
            browser.setContentType("text/html");
            browser.setText("<html>Could not load.<br>Please check your internet connection!<br><br>Try to restart the launcher.</html>");
            b_play.setEnabled(false);
        }



        b_play.setFont(b_play.getFont().deriveFont(20.0f));
        b_play.setEnabled(false);
        // Register locations of elements
        l_alert.setBounds(140, 555, 350, 20);
        b_play.setBounds(50, 585, 290, 65);
        browser.setBounds(-3,0,400,550);

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
        this.setSize(400, 715);
        this.setResizable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
