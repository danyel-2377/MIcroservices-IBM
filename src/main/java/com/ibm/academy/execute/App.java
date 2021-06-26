package com.ibm.academy.execute;

import com.ibm.academy.challenge1.HttpImage;
import com.ibm.academy.challenge2.HttpMovie;

/**
 * Hello world!
 */
public final class App {

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg/1024px-FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg.png";
        HttpImage httpImage = new HttpImage();
        System.out.println(httpImage.downloadImage(url));

        HttpMovie httpMovie = new HttpMovie();
        httpMovie.getMovies("Spiderman").stream().forEach(System.out::println);
    }
}
