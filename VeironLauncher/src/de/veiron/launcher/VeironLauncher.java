/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.veiron.launcher;


import de.veiron.launcher.manager.*;
import de.veiron.launcher.screens.LoginScreen;
import de.veiron.launcher.screens.StartScreen;

import javax.swing.*;

public class VeironLauncher {

    public final String VERSION = "a0.1";
    public final String USER_HOME = System.getProperty("user.home");

    public final String VEIRON_WINDOWS = USER_HOME + "/AppData/Roaming/.veiron";
    public final String VEIRON_MAC = USER_HOME + "/Libary/Application Support/.veiron";
    public final String VEIRON_OTHER = USER_HOME + "/.veiron";

    public final String VEIRON_APPICON_WINDOWS = VEIRON_WINDOWS + "/assets/applicationIcon.png";
    public final String VEIRON_APPICON_MAC = VEIRON_MAC + "/assets/applicationIcon.png";
    public final String VEIRON_APPICON_OTHER = VEIRON_OTHER + "/assets/applicationIcon.png";

    public SystemManager systemManager = new SystemManager();
    public LogManager logManager = new LogManager();

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoginScreen ls = new LoginScreen();
        StartScreen ss = new StartScreen();
        IconManager ai = new IconManager();
        FolderManager fm = new FolderManager();
        CredentialsManager cm = new CredentialsManager();

        if(!fm.existSystemFolder()) fm.createSystemFolder();


        if(!ai.existApplicationIcon()) ai.downloadApplicationIcon();

        if(!cm.existCredentialsFile()){
                ai.loadApplicationIcon(ls);
                ls.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ls.setLocationRelativeTo(null);
                ls.setVisible(true);
        } else {

            if(cm.getSessionHash().equals(RequestManager.getUserSessionHash(cm.getEmail()))){

                ai.loadApplicationIcon(ss);
                ss.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ss.setLocationRelativeTo(null);
                ss.setVisible(true);
            } else {
                System.out.println(RequestManager.getUserSessionHash(cm.getEmail()) + "////" + cm.getSessionHash());
                ai.loadApplicationIcon(ls);
                ls.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ls.setLocationRelativeTo(null);
                ls.setVisible(true);
            }
        }

    }
    
}
