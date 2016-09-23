package de.veiron.launcher.manager;

import de.veiron.launcher.VeironLauncher;

import java.io.File;

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
            e.printStackTrace();
            System.err.println("ERROR: Can't read or write to log file!");
            //launcher.logManager.log(Level.SEVERE, "Can't read or write to log file!");
        }
    }

    public boolean existSystemFolder() {
        try {
            if (launcher.systemManager.systemIsWindows()) {
                File f = new File(launcher.VEIRON_WINDOWS);
                return f.exists() && f.isDirectory();

            } else if (launcher.systemManager.systemIsMac()) {
                File f = new File(launcher.VEIRON_MAC);
                return f.exists() && f.isDirectory();
            } else {
                File f = new File(launcher.VEIRON_OTHER);
                return f.exists() && f.isDirectory();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR: Can't read or write to log file!");
            //launcher.logManager.log(Level.SEVERE, "Can't read or write to log file!");
        }
        return false;
    }
}
