package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.TaskDescription;
import entity.Tasks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

/**
 * Created by nikita93 on 03.07.2017.
 */
public class TaskService {

    public Tasks getTasks(Properties properties) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        String url = properties.getProperty("json_url");

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("Cookie", properties.getProperty("cookie"));

        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : ");
//        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());

        Tasks tasks = objectMapper.readValue(response.toString(), Tasks.class);

        return tasks;

    }

}
