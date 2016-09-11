package de.veiron.launcher.utils;

import de.veiron.launcher.VeironLauncher;

import java.io.File;

/**
 * Created by tompi on 11.09.2016.
 */
public class DataFolder {

    private static VeironLauncher vl = new VeironLauncher();
    private static Utilities u = new Utilities();



    public static void createDataFolder() {
        try {
            if (u.isWindows()) {
                boolean success = (new File(vl.VEIRON_WINDOWS)).mkdirs();
                if (!success) {

                }
            } else if (u.isMac()) {
                boolean success = (new File(vl.VEIRON_MAC)).mkdirs();
                if (!success) {

                }
            } else {
                boolean success = (new File(vl.VEIRON_LINUX)).mkdirs();
                if (!success) {

                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: CANNOT READ OR WRITE TO LOG FILE");
        }
    }

    public static boolean existDataFolder() {
        try {
            if (u.isWindows()) {
                File f = new File(vl.VEIRON_WINDOWS);
                if (f.exists() && f.isDirectory()) {
                    return true;
                } else {
                    return false;
                }
            } else if (u.isMac()) {
                File f = new File(vl.VEIRON_MAC);
                if (f.exists() && f.isDirectory()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                File f = new File(vl.VEIRON_LINUX);
                if (f.exists() && f.isDirectory()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: CANNOT READ OR WRITE TO LOG FILE");
        }
        return false;
    }
}
