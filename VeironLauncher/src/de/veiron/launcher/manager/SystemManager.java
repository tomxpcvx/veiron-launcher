package de.veiron.launcher.manager;

import de.veiron.launcher.VeironLauncher;

public class SystemManager {

    public boolean systemIsWindows() {
        return (System.getProperty("os.name").toLowerCase().contains("win"));
    }

    public boolean systemIsMac() {
        return (System.getProperty("os.name").toLowerCase().contains("mac"));
    }

}
