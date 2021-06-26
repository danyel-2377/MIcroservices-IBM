package com.ibm.academy.challenge1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.net.ssl.HttpsURLConnection;

public class HttpImage {

    public String downloadImage(String strUrl){
        String pathTempImage = null;
        InputStream inputStream = null;
        try {
            URL url =  new URL(strUrl);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            inputStream = httpsURLConnection.getInputStream();
            File tempFile = File.createTempFile("ibm", null);
            Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            pathTempImage = tempFile.getAbsolutePath();
        } catch (MalformedURLException e) {
            System.err.println("Error in URL");
        } catch (IOException e) {
            System.err.println("Error in IO");
        } finally {
            try {
                if(inputStream != null){
                    inputStream.close();
                }                
            } catch (IOException e) {
                System.err.println("Error in closed resources");
            }
        }
        return pathTempImage;
    }
    
}
