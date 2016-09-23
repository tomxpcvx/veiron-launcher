package de.veiron.launcher.manager;

import de.veiron.launcher.VeironLauncher;

import javax.swing.*;
import java.io.File;
import java.io.IOException;


public class IconManager {

    VeironLauncher launcher = new VeironLauncher();


    public void loadApplicationIcon(JFrame f) {

        if (this.launcher.systemManager.systemIsWindows()) {
            ImageIcon img = new ImageIcon(this.launcher.VEIRON_APPICON_WINDOWS);
            f.setIconImage(img.getImage());
        } else if (launcher.systemManager.systemIsMac()) {
            ImageIcon img = new ImageIcon(launcher.VEIRON_APPICON_MAC);
            f.setIconImage(img.getImage());
        } else {
            ImageIcon img = new ImageIcon(launcher.VEIRON_APPICON_OTHER);
            f.setIconImage(img.getImage());
        }
    }

    public boolean existApplicationIcon() {
        try {
            if (launcher.systemManager.systemIsWindows()) {
                File f = new File(launcher.VEIRON_APPICON_WINDOWS);
                return f.exists() && f.isDirectory();
            } else if (launcher.systemManager.systemIsMac()) {
                File f = new File(launcher.VEIRON_APPICON_MAC);
                return f.exists() && f.isDirectory();
            } else {
                File f = new File(launcher.VEIRON_APPICON_OTHER);
                return f.exists() && f.isDirectory();
            }
        } catch (Exception e) {
            System.err.println("ERROR: CANNOT READ OR WRITE TO LOG FILE");
        }
        return false;
    }

    public void downloadApplicationIcon() {

        String fileUrl = "https://veiron.tomtx.xyz/assets/launcher/IconJavaLauncher.png";

        DownloadManager dm = new DownloadManager();

        try {

            if (launcher.systemManager.systemIsWindows()) {
                boolean success = (new File(launcher.VEIRON_WINDOWS + "/assets")).mkdirs();
                if (success) dm.downloadFile(fileUrl, launcher.VEIRON_WINDOWS + "/assets");
            } else if (launcher.systemManager.systemIsMac()) {
                boolean success = (new File(launcher.VEIRON_MAC + "/assets")).mkdirs();
                if (success) dm.downloadFile(fileUrl, launcher.VEIRON_MAC + "/assets");
            } else {
                boolean success = (new File(launcher.VEIRON_OTHER + "/assets")).mkdirs();
                if (success) dm.downloadFile(fileUrl, launcher.VEIRON_OTHER + "/assets");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
