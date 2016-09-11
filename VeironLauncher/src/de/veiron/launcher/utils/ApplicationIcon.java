package de.veiron.launcher.utils;

import de.veiron.launcher.VeironLauncher;
import de.veiron.launcher.screens.LoginScreen;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by tompi on 11.09.2016.
 */
public class ApplicationIcon {

    private static VeironLauncher vl = new VeironLauncher();
    private static Utilities u = new Utilities();

    public static void loadApplicationIcon(JFrame lf) {



                if (u.isWindows()) {
                    ImageIcon img = new ImageIcon(vl.VEIRON_APPICON_WINDOWS);
                    lf.setIconImage(img.getImage());
                } else if (u.isMac()) {
                    ImageIcon img = new ImageIcon(vl.VEIRON_APPICON_MAC);
                    lf.setIconImage(img.getImage());
                } else {
                    ImageIcon img = new ImageIcon(vl.VEIRON_APPICON_LINUX);
                    lf.setIconImage(img.getImage());
                }


    }

    public static boolean existApplicationIcon() {
        try {
            if (u.isWindows()) {
                File f = new File(vl.VEIRON_APPICON_WINDOWS);
                if (f.exists() && f.isDirectory()) {
                    return true;
                } else {
                    return false;
                }
            } else if (u.isMac()) {
                File f = new File(vl.VEIRON_APPICON_MAC);
                if (f.exists() && f.isDirectory()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                File f = new File(vl.VEIRON_APPICON_LINUX);
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

    public static void downloadApplicationIcon() {

        String fileUrl = "https://veiron.tomtx.xyz/launcher/assets/applicationIcon.png";

        if (u.isWindows()) {
            try {
                boolean success = (new File(vl.VEIRON_WINDOWS + "/assets")).mkdirs();
                if(success == true){
                    UrlDownload.downloadFile(fileUrl, vl.VEIRON_WINDOWS + "/assets");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (u.isMac()) {
            try {
                boolean success = (new File(vl.VEIRON_MAC + "/assets")).mkdirs();
                if(success == true){
                    UrlDownload.downloadFile(fileUrl, vl.VEIRON_MAC + "/assets");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                boolean success = (new File(vl.VEIRON_LINUX + "/assets")).mkdirs();
                if (success == true) {
                    UrlDownload.downloadFile(fileUrl, vl.VEIRON_LINUX + "/assets");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
