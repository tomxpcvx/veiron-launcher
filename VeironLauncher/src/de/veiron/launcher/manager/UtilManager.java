/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.veiron.launcher.manager;

import de.veiron.launcher.VeironLauncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tompi
 */
public class UtilManager {

    public void registerJLabelLink(JLabel website, final String url, String text) {
        website.setText("<html><a href=\"\">" + text + "</a></html>");
        website.setCursor(new Cursor(Cursor.HAND_CURSOR));
        website.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (URISyntaxException | IOException ex) {
                    //It looks like there's a problem
                }
            }
        });
    }
    public void changeJLabelMessage(JLabel label, String message, int w, int x, int y, int z){
        label.setText(message);
        label.setBounds(w, x, y, z);
        label.repaint();
    }



}
