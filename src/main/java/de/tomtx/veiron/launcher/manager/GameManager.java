package de.tomtx.veiron.launcher.manager;

import de.tomtx.veiron.launcher.VeironLauncher;
import de.tomtx.veiron.launcher.manager.DownloadManager;

import java.io.File;

public class GameManager {

    VeironLauncher lauchner = new VeironLauncher();
    DownloadManager dm = new DownloadManager();

    public boolean existGameData() {


        try {
            if (lauchner.systemManager.systemIsWindows()) {
                File f = new File(lauchner.VEIRON_WINDOWS + "/veiron.jar");
                return f.exists() && f.isFile();
            } else if (lauchner.systemManager.systemIsMac()) {
                File f = new File(lauchner.VEIRON_MAC + "/veiron.jar");
                return f.exists() && f.isFile();
            } else {
                File f = new File(lauchner.VEIRON_OTHER + "/veiron.jar");
                return f.exists() && f.isFile();
            }
        } catch (Exception e) {
            System.err.println("ERROR: CANNOT READ OR WRITE TO LOG FILE");
        }
        return false;
    }

    public void downloadGameData() {

        String fileUrl = "https://veiron.tomtx.xyz/game/assets/veiron.jar";

        if (lauchner.systemManager.systemIsWindows()) {
            try {
                dm.downloadFile(fileUrl, lauchner.VEIRON_WINDOWS);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (lauchner.systemManager.systemIsMac()) {
            try {
                dm.downloadFile(fileUrl, lauchner.VEIRON_MAC);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                dm.downloadFile(fileUrl, lauchner.VEIRON_OTHER);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void startGame() {


        if (lauchner.systemManager.systemIsWindows()) {
            try {
                Runtime.getRuntime().exec("java -jar " + lauchner.VEIRON_WINDOWS + "/veiron.jar");
            } catch (Exception ex) {

            }
        } else if (lauchner.systemManager.systemIsMac()) {
            try {
                Runtime.getRuntime().exec("java -jar " + lauchner.VEIRON_MAC + "/veiron.jar");
            } catch (Exception ex) {

            }
        } else {
            try {
                Runtime.getRuntime().exec("java -jar " + lauchner.VEIRON_OTHER + "/veiron.jar");
            } catch (Exception ex) {

            }
        }

    }


}
