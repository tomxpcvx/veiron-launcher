package de.veiron.launcher.utils;

import javax.swing.*;
import java.io.File;

/**
 * Created by tompi on 11.09.2016.
 */
public class DataFolder {

    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String HOME = System.getProperty("user.home");

    public static void createDataFolder() {
        try {
            if (isWindows()) {
                boolean success = (new File(HOME + "/AppData/Roaming/.veiron")).mkdirs();
                if (!success) {

                }
            } else if (isMac()) {
                boolean success = (new File(HOME + "/Libary/Application Support/.veiron")).mkdirs();
                if (!success) {

                }
            } else if (isLinux()) {
                boolean success = (new File(HOME + "/.veiron")).mkdirs();
                if (!success) {

                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: CANNOT READ OR WRITE TO LOG FILE");
        }
    }

    public static boolean getDataFolder() {
        try {
            if (isWindows()) {
                File f = new File(HOME + "/AppData/Roaming/.veiron");
                if (f.exists() && f.isDirectory()) {
                    return true;
                } else {
                    return false;
                }
            } else if (isMac()) {
                File f = new File(HOME + "/Libary/Application Support/.veiron");
                if (f.exists() && f.isDirectory()) {
                    return true;
                } else {
                    return false;
                }
            } else if (isLinux()) {
                File f = new File(HOME + "/.veiron");
                if (f.exists() && f.isDirectory()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                File f = new File(HOME + "/.veiron");
                if (f.exists() && f.isDirectory()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: CANNOT READ OR WRITE TO LOG FILE");
        }
    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isLinux() {
        return (OS.indexOf("linux") >= 0);
    }

}
