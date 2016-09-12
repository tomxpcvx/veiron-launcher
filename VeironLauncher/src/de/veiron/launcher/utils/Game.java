package de.veiron.launcher.utils;

import de.veiron.launcher.VeironLauncher;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by tompi on 12.09.2016.
 */
public class Game {

    private static VeironLauncher vl = new VeironLauncher();
    private static Utilities u = new Utilities();

    public static boolean existGameData() {


        try {
            if (u.isWindows()) {
                File f = new File(vl.VEIRON_WINDOWS + "/veiron.jar");
                if (f.exists() && f.isFile()) {
                    return true;
                } else {
                    return false;
                }
            } else if (u.isMac()) {
                File f = new File(vl.VEIRON_MAC + "/veiron.jar");
                if (f.exists() && f.isFile()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                File f = new File(vl.VEIRON_LINUX + "/veiron.jar");
                if (f.exists() && f.isFile()) {
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

    public static void downloadGameData() {

        String fileUrl = "https://veiron.tomtx.xyz/game/assets/veiron.jar";

        if (u.isWindows()) {
            try {
                UrlDownload.downloadFile(fileUrl, vl.VEIRON_WINDOWS);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (u.isMac()) {
            try {
                UrlDownload.downloadFile(fileUrl, vl.VEIRON_MAC);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                UrlDownload.downloadFile(fileUrl, vl.VEIRON_LINUX);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void startGame() {


        if (u.isWindows()) {
            try {
                Runtime.getRuntime().exec("java -jar " + vl.VEIRON_WINDOWS + "/veiron.jar");
            } catch (Exception ex) {

            }
        } else if (u.isMac()) {
            try {
            Runtime.getRuntime().exec("java -jar " + vl.VEIRON_MAC + "/veiron.jar");
            } catch (Exception ex) {

            }
        } else {
            try {
            Runtime.getRuntime().exec("java -jar " + vl.VEIRON_LINUX + "/veiron.jar");
            } catch (Exception ex) {

            }
        }

    }


}
