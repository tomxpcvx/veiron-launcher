package de.tomtx.veiron.launcher.manager;

import de.tomtx.veiron.launcher.VeironLauncher;

import java.io.File;
import java.util.logging.Level;

public class FolderManager {

    VeironLauncher launcher = new VeironLauncher();

    public void createSystemFolder() {

        try {
            if (launcher.systemManager.systemIsWindows()) {
                boolean success = (new File(launcher.VEIRON_WINDOWS)).mkdirs();
                if (!success) {

                }
            } else if (launcher.systemManager.systemIsMac()) {
                boolean success = (new File(launcher.VEIRON_MAC)).mkdirs();
                if (!success) {

                }
            } else {
                boolean success = (new File(launcher.VEIRON_OTHER)).mkdirs();
                if (!success) {

                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: Can't read or write to log file!");
            //launcher.logManager.log(Level.SEVERE, "Can't read or write to log file!");
        }
    }

    public boolean existSystemFolder() {
        try {
            if (launcher.systemManager.systemIsWindows()) {
                File f = new File(launcher.VEIRON_WINDOWS);
                if (f.exists()) if (f.isDirectory()) return true;
                return false;
            } else if (launcher.systemManager.systemIsMac()) {
                File f = new File(launcher.VEIRON_MAC);
                if (f.exists()) if (f.isDirectory()) return true;
                return false;
            } else {
                File f = new File(launcher.VEIRON_OTHER);
                if (f.exists()) if (f.isDirectory()) return true;
                return false;
            }
        } catch (Exception e) {
            System.err.println("ERROR: Can't read or write to log file!");
            //launcher.logManager.log(Level.SEVERE, "Can't read or write to log file!");
        }
        return false;
    }
}
