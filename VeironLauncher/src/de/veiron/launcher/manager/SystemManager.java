package de.veiron.launcher.manager;

import de.veiron.launcher.VeironLauncher;

/**
 * Created by tompi on 17.09.2016.
 */
public class SystemManager {

    public boolean systemIsWindows() {
        return (System.getProperty("os.name").toLowerCase().contains("win"));
    }

    public boolean systemIsMac() {
        return (System.getProperty("os.name").toLowerCase().contains("mac"));
    }

}
