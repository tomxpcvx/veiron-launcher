/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.veiron.launcher;

import de.veiron.launcher.manager.FolderManager;
import de.veiron.launcher.manager.LogManager;
import de.veiron.launcher.screens.LoginScreen;
import de.veiron.launcher.manager.IconManager;
import de.veiron.launcher.manager.SystemManager;

import javax.swing.*;


public class VeironLauncher {

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
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        LoginScreen ls = new LoginScreen();
        IconManager ai = new IconManager();
        FolderManager fm = new FolderManager();


        if(!fm.existSystemFolder()) fm.createSystemFolder();

        if(!ai.existApplicationIcon()) ai.downloadApplicationIcon();

        ai.loadApplicationIcon(ls);
        ls.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ls.setLocationRelativeTo(null);
        ls.setVisible(true);
    }
    
}
