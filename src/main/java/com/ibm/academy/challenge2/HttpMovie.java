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

    public List<Movie> getMovies(String title) {

        int page = 1;
        int pages = 0;
        List<Movie> movies = new ArrayList<>();
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
                pages = ((Long) jsonObject.get("total_pages")).intValue();
                JSONArray jsonMovies = (JSONArray) jsonObject.get("data");
                Iterator<JSONObject> iterator = jsonMovies.iterator();
                while (iterator.hasNext()) {
                    JSONObject jsonMovie = iterator.next();
                    Movie movie = new Movie();
                    movie.setTitle((String) jsonMovie.get("Title"));
                    movie.setYear(((Long) jsonMovie.get("Year")).intValue());
                    movie.setImbId((String) jsonMovie.get("imdbID"));
                    movies.add(movie);
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
