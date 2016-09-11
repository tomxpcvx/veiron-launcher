/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.veiron.launcher;

import de.veiron.launcher.screens.LoginScreen;
import de.veiron.launcher.utils.ApplicationIcon;
import de.veiron.launcher.utils.DataFolder;

import javax.swing.*;

/**
 * Created by tompi on 08.09.2016.
 */
public class VeironLauncher {

    public static final String OS = System.getProperty("os.name").toLowerCase();
    public static final String USER_HOME = System.getProperty("user.home");

    public static final String VEIRON_WINDOWS = VeironLauncher.USER_HOME + "/AppData/Roaming/.veiron";
    public static final String VEIRON_MAC = VeironLauncher.USER_HOME + "/Libary/Application Support/.veiron";
    public static final String VEIRON_LINUX = VeironLauncher.USER_HOME + "/.veiron";

    public static final String VEIRON_APPICON_WINDOWS = VEIRON_WINDOWS + "/assets/applicationIcon.png";
    public static final String VEIRON_APPICON_MAC = VEIRON_MAC + "/assets/applicationIcon.png";
    public static final String VEIRON_APPICON_LINUX = VEIRON_LINUX + "/assets/applicationIcon.png";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }

        LoginScreen ls = new LoginScreen();

        if(DataFolder.existDataFolder() == false) {
            DataFolder.createDataFolder();
        }
        if(ApplicationIcon.existApplicationIcon() == false){
            ApplicationIcon.downloadApplicationIcon();
        }
        ApplicationIcon.loadApplicationIcon(ls);

        ls.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ls.setLocationRelativeTo(null);
        ls.setVisible(true);
    }
    
}
