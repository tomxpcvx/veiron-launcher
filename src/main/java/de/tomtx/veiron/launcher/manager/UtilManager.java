package de.tomtx.veiron.launcher.manager;


import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public String getHashCodeForFile(File file) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(Files.readAllBytes(Paths.get(file.getPath())));
            byte[] digest = md.digest();
            String digestInHex = DatatypeConverter.printHexBinary(digest).toUpperCase();
            System.out.println(digestInHex);
            return digestInHex;
        } catch (NoSuchAlgorithmException | IOException e) {
            e.setStackTrace(e.getStackTrace());
            return "";
        }
    }

    public String getHashForString(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(string.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            String digestInHex = DatatypeConverter.printHexBinary(digest).toUpperCase();
            System.out.println(digestInHex);
            return digestInHex;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.setStackTrace(e.getStackTrace());
            return "";
        }
    }



}
