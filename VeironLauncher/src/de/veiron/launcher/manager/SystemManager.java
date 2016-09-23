package de.veiron.launcher.manager;

public class SystemManager {

    public boolean systemIsWindows() {
        return (System.getProperty("os.name").toLowerCase().contains("win"));
    }

    public boolean systemIsMac() {
        return (System.getProperty("os.name").toLowerCase().contains("mac"));
    }

}
