package de.veiron.launcher.php;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by tompi on 11.09.2016.
 */
public class PHPLoginUser {

    public static boolean hasUserRegistered(String email, String password) {

        boolean accepted = false;
        JSONParser parser = new JSONParser();

        try {
            String url = "https://veiron.tomtx.xyz/rest/get/hasUserRegistered.php";
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

            System.out.println(out.toString());
            reader.close();

            JSONArray jsonArray = (JSONArray) parser.parse(out.toString());

            JSONObject jsonObject = (JSONObject) jsonArray.get(0);

            accepted = (jsonObject.get("isActive").equals("false") ? true : false);

        }
        catch(Exception e){

        }
        return accepted;
    }

}
