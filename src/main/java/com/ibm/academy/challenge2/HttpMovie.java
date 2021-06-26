package com.ibm.academy.challenge2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HttpMovie {

    private final String BASE_URL = "https://jsonmock.hackerrank.com/api/movies/search/?";

    public List<String> getMovies(String title) {

        long page = 1;
        long pages = -1;
        List<String> movies = new ArrayList<>();
        do {
            InputStream inputStream = null;
            Reader reader = null;
            try {
                URL url = new URL(getUrl(page, title));
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                inputStream = httpsURLConnection.getInputStream();
                reader = new InputStreamReader(inputStream);
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
                pages = (Long) jsonObject.get("total_pages");
                JSONArray jsonMovies = (JSONArray) jsonObject.get("data");
                Iterator<JSONObject> iterator = jsonMovies.iterator();
                while (iterator.hasNext()) {
                    movies.add((String) iterator.next().get("Title"));
                }
                page++;
            } catch (MalformedURLException e) {
                System.err.println("Error in URL");
            } catch (IOException e) {
                System.err.println("Error in parse response");
            } catch (ParseException e) {
                System.err.println("Error in parse response");
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    System.err.println("Error in closed resources");
                }
            }
        } while (page <= pages);

        return movies;
    }

    private String getUrl(long page, String title) {
        return BASE_URL + "Title=" + title + "&page=" + page;
    }

}
