package de.veiron.launcher.screens;

import de.veiron.launcher.manager.*;
import de.veiron.launcher.manager.RequestManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginScreen extends JFrame implements ActionListener {

    LoginManager lm = new LoginManager();
    UtilManager um = new UtilManager();
    GameManager gm = new GameManager();
    IconManager im = new IconManager();
    CredentialsManager cm = new CredentialsManager();


    JButton b_login;
    JFrame frame = this;
    JTextField tb_email;
    JPasswordField pf_password;
    JLabel l_email, l_password, l_alert;

    public LoginScreen() {

        // All elements
        JButton b_login = new JButton("Login");
        JLabel l_email = new JLabel("E-Mail:");
        JLabel l_password = new JLabel("Passwort:");
        JLabel l_alert = new JLabel("Melde dich mit deinem Veiron-Konto an.");
        JLabel l_registerLink = new JLabel("");
        JLabel l_register = new JLabel("Du hast noch kein Konto?");

        JTextField tb_email = new JTextField();
        JPasswordField pf_password = new JPasswordField();

        if (cm.existCredentialsFile()) {
            tb_email.setText(cm.getEmail());
            pf_password.setText(cm.getSessionHash());
        }

        this.setLayout(null);

        JEditorPane browser = new JEditorPane();
        browser.setEditable(false);

        try {
            browser.setPage("https://veiron.tomtx.xyz/assets/launcher/changelog.html");
        } catch (IOException e) {
            browser.setContentType("text/html");
            browser.setText("<html>Could not load.<br>Please check your internet connection!<br><br>Try to restart the launcher.</html>");
            b_login.setEnabled(false);
        }


        // Preferences for elements
        um.registerJLabelLink(l_registerLink, "https://veiron.tomtx.xyz/register", "Jetzt registrieren!");


        Font boldFont = new Font(b_login.getFont().getName(), Font.BOLD, 15);

        l_alert.setFont(l_alert.getFont().deriveFont(14.0f));
        l_email.setFont(l_email.getFont().deriveFont(14.0f));
        l_password.setFont(l_password.getFont().deriveFont(14.0f));
        b_login.setFont(boldFont);
        l_register.setFont(l_register.getFont().deriveFont(14.0f));
        l_registerLink.setFont(l_registerLink.getFont().deriveFont(14.0f));


        // Register locations of elements
        l_email.setBounds(30, 490, 120, 20);
        l_password.setBounds(10, 525, 120, 20);
        l_alert.setBounds(60, 460, 350, 20);
        l_registerLink.setBounds(250, 552, 250, 20);
        l_register.setBounds(60, 552, 250, 20);
        tb_email.setBounds(90, 485, 180, 30);
        pf_password.setBounds(90, 520, 180, 30);
        b_login.setBounds(275, 485, 100, 65);
        browser.setBounds(-3,-3,400,455);

        // Register Action Listener for button
        b_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lm.validateEmail(tb_email.getText())) {
                    if (!pf_password.getText().isEmpty()) {
                        if (RequestManager.hasUserRegistered(tb_email.getText(), pf_password.getText())) {
                            if (RequestManager.hasUserPaid(tb_email.getText())) {

                                RequestManager.setUserSessionHash(tb_email.getText());

                                if(!gm.existGameData()){
                                    gm.downloadGameData();
                                }

                                if(!cm.existCredentialsFile()){
                                    cm.createCredentialsFile();
                                }

                                StartScreen sc = new StartScreen();

                                cm.saveCredentialsToFile(tb_email.getText(), RequestManager.getUserSessionHash(tb_email.getText()));
                                frame.setVisible(false);
                                im.loadApplicationIcon(sc);
                                sc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                sc.setLocationRelativeTo(null);
                                sc.setVisible(true);

                            } else {
                                um.changeJLabelMessage(l_alert, "Du musst das Spiel erwerben!", 80, 460, 350, 20);
                            }
                        } else {
                            um.changeJLabelMessage(l_alert, "Überprüfe bitte deine Eingaben!", 80, 460, 350, 20);
                        }
                    } else {
                        um.changeJLabelMessage(l_alert, "Du musst ein Passwort angeben!", 80, 460, 350, 20);
                    }
                } else {
                    um.changeJLabelMessage(l_alert, "Diese E-Mail ist nicht gültig!", 95, 460, 350, 20);
                }
            }
        });

        // Add all elements in JFrame
        this.add(l_email);
        this.add(l_password);
        this.add(l_alert);
        this.add(l_registerLink);
        this.add(l_register);
        this.add(tb_email);
        this.add(pf_password);
        this.add(b_login);
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
