package de.tomtx.veiron.launcher.manager;

import de.tomtx.veiron.launcher.VeironLauncher;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Iterator;

public class CredentialsManager {

    VeironLauncher launcher = new VeironLauncher();
    UtilManager um = new UtilManager();

    public void createCredentialsFile() {

        try {
            if (launcher.systemManager.systemIsWindows()) {
                boolean success = (new File(launcher.VEIRON_WINDOWS + "/credentials.json").createNewFile());
                if (!success) {

                }
            } else if (launcher.systemManager.systemIsMac()) {
                boolean success = (new File(launcher.VEIRON_MAC + "/credentials.json").createNewFile());
                if (!success) {

                }
            } else {
                boolean success = (new File(launcher.VEIRON_OTHER + "/credentials.json").createNewFile());
                if (!success) {

                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: Can't read or write to log file!");
            //launcher.logManager.log(Level.SEVERE, "Can't read or write to log file!");
        }
    }

    public boolean existCredentialsFile() {
        try {
            if (launcher.systemManager.systemIsWindows()) {
                File f = new File(launcher.VEIRON_WINDOWS + "/credentials.json");
                if (f.exists()) if (f.isFile()) return true;
            } else if (launcher.systemManager.systemIsMac()) {
                File f = new File(launcher.VEIRON_MAC + "/credentials.json");
                if (f.exists()) if (f.isFile()) return true;
            } else {
                File f = new File(launcher.VEIRON_OTHER + "/credentials.json");
                if (f.exists()) if (f.isFile()) return true;
            }
        } catch (Exception e) {
            System.err.println("ERROR: Can't read or write to log file!");
            //launcher.logManager.log(Level.SEVERE, "Can't read or write to log file!");
        }
        return false;
    }

    public boolean saveCredentialsToFile(String email, String sessionHash) {
        try {
            if (launcher.systemManager.systemIsWindows()) {
                File f = new File(launcher.VEIRON_WINDOWS + "/credentials.json");

                if (f.exists() && f.isFile()) {

                    JSONObject obj = new JSONObject();

                    JSONArray user = new JSONArray();
                    user.add("email: " + email);
                    user.add("username: " + RequestManager.getUserIngameName(email));
                    user.add("sessionHash: " + sessionHash);
                    obj.put("user", user);

                    JSONArray la = new JSONArray();
                    la.add("version: " + launcher.VERSION);
                    la.add("launcherHash: " + um.getHashCodeForFile(new File(launcher.VEIRON_WINDOWS + "/launcher.jar")));
                    la.add("gameHash: " + um.getHashCodeForFile(new File(launcher.VEIRON_WINDOWS + "/veiron.jar")));
                    obj.put("gameData", la);

                    FileWriter file = new FileWriter(launcher.VEIRON_WINDOWS + "/credentials.json");
                    file.write(obj.toJSONString());
                    file.flush();
                    file.close();
                    System.out.println("Successfully Copied JSON Object to File...");
                    System.out.println("\nJSON Object: " + obj);

                    return true;
                }

            } else if (launcher.systemManager.systemIsMac()) {
                File f = new File(launcher.VEIRON_MAC + "/credentials.json");
                if (f.exists() && f.isFile()) {

                    JSONObject obj = new JSONObject();

                    JSONArray user = new JSONArray();
                    user.add("email: " + email);
                    user.add("username: " + RequestManager.getUserIngameName(email));
                    user.add("sessionHash: " + sessionHash);
                    obj.put("user", user);

                    JSONArray la = new JSONArray();
                    la.add("version: " + launcher.VERSION);
                    la.add("launcherHash: " + um.getHashCodeForFile(new File(launcher.VEIRON_MAC + "/launcher.jar")));
                    la.add("gameHash: " + um.getHashCodeForFile(new File(launcher.VEIRON_MAC + "/veiron.jar")));
                    obj.put("gameData", la);

                    FileWriter file = new FileWriter(launcher.VEIRON_MAC + "/credentials.json");
                    BufferedWriter bw = new BufferedWriter(file);
                    bw.write(obj.toJSONString());
                    bw.close();
                    System.out.println("Successfully Copied JSON Object to File...");
                    System.out.println("\nJSON Object: " + obj);

                    return true;
                }
            } else {
                File f = new File(launcher.VEIRON_OTHER + "/credentials.json");
                if (f.exists() && f.isFile() && f.delete()) {

                    JSONObject obj = new JSONObject();

                    JSONArray user = new JSONArray();
                    user.add("email: " + email);
                    user.add("username: " + RequestManager.getUserIngameName(email));
                    user.add("sessionHash: " + sessionHash);
                    obj.put("user", user);

                    JSONArray la = new JSONArray();
                    la.add("version: " + launcher.VERSION);
                    la.add("launcherHash: " + um.getHashCodeForFile(new File(launcher.VEIRON_OTHER + "/launcher.jar")));
                    la.add("gameHash: " + um.getHashCodeForFile(new File(launcher.VEIRON_OTHER + "/veiron.jar")));
                    obj.put("gameData", la);

                    FileWriter file = new FileWriter(launcher.VEIRON_OTHER + "/credentials.json");
                    file.write(obj.toJSONString());
                    System.out.println("Successfully Copied JSON Object to File...");
                    System.out.println("\nJSON Object: " + obj);

                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: Can't read or write to log file!");
            //launcher.logManager.log(Level.SEVERE, "Can't read or write to log file!");
        }
        return false;
    }

    public String getEmail() {
        JSONParser parser = new JSONParser();
        try {
            if (launcher.systemManager.systemIsWindows()) {
                Object obj = parser.parse(new FileReader(launcher.VEIRON_WINDOWS + "/credentials.json"));
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray user = (JSONArray) jsonObject.get("user");
                Iterator<String> iterator = user.iterator();
                return iterator.next().replace("email: ", "");
            } else if (launcher.systemManager.systemIsMac()) {
                Object obj = parser.parse(new FileReader(launcher.VEIRON_MAC + "/credentials.json"));
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray user = (JSONArray) jsonObject.get("user");
                Iterator<String> iterator = user.iterator();
                return iterator.next().replace("email: ", "");
            } else {
                Object obj = parser.parse(new FileReader(launcher.VEIRON_OTHER + "/credentials.json"));
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray user = (JSONArray) jsonObject.get("user");
                Iterator<String> iterator = user.iterator();
                return iterator.next().replace("email: ", "");
            }
        } catch (Exception e) {
            return "";
        }
    }

    public String getSessionHash() {
        JSONParser parser = new JSONParser();
        try {
            if (launcher.systemManager.systemIsWindows()) {
                Object obj = parser.parse(new FileReader(launcher.VEIRON_WINDOWS + "/credentials.json"));
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray user = (JSONArray) jsonObject.get("user");
                Iterator<String> iterator = user.iterator();
                iterator.next();
                iterator.next();
                return iterator.next().replace("sessionHash: ", "");
            } else if (launcher.systemManager.systemIsMac()) {
                Object obj = parser.parse(new FileReader(launcher.VEIRON_MAC + "/credentials.json"));
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray user = (JSONArray) jsonObject.get("user");
                Iterator<String> iterator = user.iterator();
                iterator.next();
                iterator.next();
                return iterator.next().replace("sessionHash: ", "");
            } else {
                Object obj = parser.parse(new FileReader(launcher.VEIRON_OTHER + "/credentials.json"));
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray user = (JSONArray) jsonObject.get("user");
                Iterator<String> iterator = user.iterator();
                iterator.next();
                iterator.next();
                return iterator.next().replace("sessionHash: ", "");
            }
        } catch (Exception e) {
            return "";
        }
    }

    public String getIngameName() {
        JSONParser parser = new JSONParser();
        try {
            if (launcher.systemManager.systemIsWindows()) {
                Object obj = parser.parse(new FileReader(launcher.VEIRON_WINDOWS + "/credentials.json"));
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray user = (JSONArray) jsonObject.get("user");
                Iterator<String> iterator = user.iterator();
                iterator.next();
                return iterator.next().replace("username: ", "");
            } else if (launcher.systemManager.systemIsMac()) {
                Object obj = parser.parse(new FileReader(launcher.VEIRON_MAC + "/credentials.json"));
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray user = (JSONArray) jsonObject.get("user");
                Iterator<String> iterator = user.iterator();
                iterator.next();
                return iterator.next().replace("username: ", "");
            } else {
                Object obj = parser.parse(new FileReader(launcher.VEIRON_OTHER + "/credentials.json"));
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray user = (JSONArray) jsonObject.get("user");
                Iterator<String> iterator = user.iterator();
                iterator.next();
                return iterator.next().replace("username: ", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
