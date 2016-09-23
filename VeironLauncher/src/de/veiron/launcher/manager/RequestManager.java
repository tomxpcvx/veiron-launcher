package de.veiron.launcher.manager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class RequestManager {

    public static String setUserSessionHash(String email) {

        String string;
        JSONParser parser = new JSONParser();

        try {

            String url = "https://veiron.tomtx.xyz/api/set/UserSessionHash.php";
            String charset = "UTF-8";

            String query = String.format("email=%s",
                    URLEncoder.encode(email, charset));

            URLConnection connection = new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

            try (OutputStream output = connection.getOutputStream()) {
                output.write(query.getBytes(charset));
            }

            InputStream response = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(response));
            StringBuilder out = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                out.append(line);
            }

            reader.close();

            JSONArray jsonArray = (JSONArray) parser.parse(out.toString());

            JSONObject jsonObject = (JSONObject) jsonArray.get(0);

            string = jsonObject.get("sessionId").toString();
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public static String getUserSessionHash(String email) {

        String string;
        JSONParser parser = new JSONParser();

        try {
            String url = "https://veiron.tomtx.xyz/api/get/UserSessionHash.php";
            String charset = "UTF-8";

            String query = String.format("email=%s",
                    URLEncoder.encode(email, charset));

            URLConnection connection = new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

            try (OutputStream output = connection.getOutputStream()) {
                output.write(query.getBytes(charset));
            }

            InputStream response = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(response));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }

            reader.close();

            JSONArray jsonArray = (JSONArray) parser.parse(out.toString());

            JSONObject jsonObject = (JSONObject) jsonArray.get(0);

            string = jsonObject.get("sessionId").toString();
            return string;
        }
        catch(Exception e){
            e.setStackTrace(e.getStackTrace());
            return "";
        }

    }

    public static String getUserIngameName(String email) {

        String string;
        JSONParser parser = new JSONParser();

        try {
            String url = "https://veiron.tomtx.xyz/api/get/UserIngameName.php";
            String charset = "UTF-8";

            String query = String.format("email=%s",
                    URLEncoder.encode(email, charset));

            URLConnection connection = new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

            try (OutputStream output = connection.getOutputStream()) {
                output.write(query.getBytes(charset));
            }

            InputStream response = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(response));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }


            reader.close();

            JSONArray jsonArray = (JSONArray) parser.parse(out.toString());

            JSONObject jsonObject = (JSONObject) jsonArray.get(0);

            string = jsonObject.get("username").toString();

            return string;
        }
        catch(Exception e){
            e.setStackTrace(e.getStackTrace());
            return "";
        }

    }

    public static boolean hasUserPaid(String email) {

        boolean accepted = false;
        JSONParser parser = new JSONParser();

        try {
            String url = "https://veiron.tomtx.xyz/api/get/UserPaidState.php";
            String charset = "UTF-8";

            String query = String.format("email=%s",
                    URLEncoder.encode(email, charset));

            URLConnection connection = new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

            try (OutputStream output = connection.getOutputStream()) {
                output.write(query.getBytes(charset));
            }

            InputStream response = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(response));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }


            reader.close();

            JSONArray jsonArray = (JSONArray) parser.parse(out.toString());

            JSONObject jsonObject = (JSONObject) jsonArray.get(0);

            accepted = (jsonObject.get("boughtGame").equals("true"));

        }
        catch(Exception e){

        }
        return accepted;
    }

    public static boolean hasUserRegistered(String email, String password) {

        boolean accepted = false;
        JSONParser parser = new JSONParser();

        try {
            String url = "https://veiron.tomtx.xyz/api/get/UserRegisterState.php";
            String charset = "UTF-8";

            String query = String.format("email=%s&password=%s",
                    URLEncoder.encode(email, charset),
                    URLEncoder.encode(password, charset));

            URLConnection connection = new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

            try (OutputStream output = connection.getOutputStream()) {
                output.write(query.getBytes(charset));
            }

            InputStream response = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(response));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }

            reader.close();

            JSONArray jsonArray = (JSONArray) parser.parse(out.toString());

            JSONObject jsonObject = (JSONObject) jsonArray.get(0);

            accepted = (jsonObject.get("isActive").equals("false"));

        }
        catch(Exception e){

        }
        return accepted;
    }

}