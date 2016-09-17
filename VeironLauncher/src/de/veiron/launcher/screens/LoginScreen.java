package de.veiron.launcher.screens;

import de.veiron.launcher.manager.GameManager;
import de.veiron.launcher.manager.LoginManager;
import de.veiron.launcher.manager.UtilManager;
import de.veiron.launcher.php.PHPHasUserPaid;
import de.veiron.launcher.php.PHPLoginUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginScreen extends JFrame implements ActionListener {

    LoginManager lm = new LoginManager();
    UtilManager um = new UtilManager();
    GameManager gm = new GameManager();

    JButton b_login;
    JTextField tb_email;
    JPasswordField pf_password;
    JLabel l_email, l_password, l_alert;

    public LoginScreen() {

        // All elements
        JButton b_login = new JButton("Login");
        JTextField tb_email = new JTextField();
        JPasswordField pf_password = new JPasswordField();
        JLabel l_email = new JLabel("E-Mail:");
        JLabel l_password = new JLabel("Passwort:");
        JLabel l_alert = new JLabel("Melde dich mit deinem Veiron-Konto an.");
        JLabel l_registerLink = new JLabel("");
        JLabel l_register = new JLabel("Du hast noch kein Konto?");

        this.setLayout(null);

        JEditorPane browser = new JEditorPane();
        browser.setEditable(false);

        try {
            browser.setPage("https://veiron.tomtx.xyz/launcher/changelog");
        } catch (IOException e) {
            browser.setContentType("text/html");
            browser.setText("<html>Could not load.<br>Please check your internet connection!<br><br>Try to restart the launcher.</html>");
            b_login.setEnabled(false);
        }


        // Preferences for elements
        um.registerJLabelLink(l_registerLink, "https://veiron.tomtx.xyz/register", "Jetzt registrieren!");

        //l_register.setFont(l_register.getFont().deriveFont(14.0f));
        //l_registerLink.setFont(l_registerLink.getFont().deriveFont(14.0f));


        // Register locations of elements
        l_email.setBounds(30, 590, 120, 20);
        l_password.setBounds(10, 625, 120, 20);
        l_alert.setBounds(60, 555, 350, 20);
        l_registerLink.setBounds(250, 652, 250, 20);
        l_register.setBounds(60, 652, 250, 20);
        tb_email.setBounds(90, 585, 180, 30);
        pf_password.setBounds(90, 620, 180, 30);
        b_login.setBounds(275, 585, 100, 65);
        browser.setBounds(-3,0,400,550);

        // Register Action Listener for button
        b_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lm.validateEmail(tb_email.getText())) {
                    if (!pf_password.getText().isEmpty()) {
                        if (PHPLoginUser.hasUserRegistered(tb_email.getText(), pf_password.getText())) {
                            if (PHPHasUserPaid.hasUserPaid(tb_email.getText())) {
                                um.changeJLabelMessage(l_alert, "Das Spiel wird gestartet!", 110, 555, 350, 20);

                                if (!gm.existGameData()) {
                                    gm.downloadGameData();
                                }
                                gm.startGame();

                            } else {
                                um.changeJLabelMessage(l_alert, "Du musst das Spiel erwerben!", 80, 555, 350, 20);
                            }
                        } else {
                            um.changeJLabelMessage(l_alert, "Überprüfe bitte deine Eingaben!", 80, 555, 350, 20);
                        }
                    } else {
                        um.changeJLabelMessage(l_alert, "Du musst ein Passwort angeben!", 80, 555, 350, 20);
                    }
                } else {
                    um.changeJLabelMessage(l_alert, "Diese E-Mail ist nicht gültig!", 95, 555, 350, 20);
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
        this.setSize(400, 715);
        this.setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {

    }

}
