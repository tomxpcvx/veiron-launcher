/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.veiron.launcher;

import javax.swing.UIManager;

/**
 * Created by tompi on 08.09.2016.
 */
public class VeironLauncher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {
            
        }
        new LoginScreen("Veiron Launcher").setVisible(true);
    }
    
}
