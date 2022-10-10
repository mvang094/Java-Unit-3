package com.javaunit3.springmvc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//Add appropriate annotation to the MovieApp so that the project package is scanned for Spring Components
@ComponentScan
public class MovieApp {
    public static void main(String[] args)
    {
        //Get the application context
        //This is the Spring Container; Bean factory
        //Instead of using an XML file, it uses an annotated class
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MovieApp.class);

        //This is similar to doing:
        //BestMovieServer bestMovieService = new BestMovieService();
        //Except the app access it with Spring, thus the applicationContext.
        //Recall that "bestMovieService" is the id of the bean. Must match exactly
        //the bean is made my the IDE.
        //BestMovieService.class is the class
        BestMovieService bestMovieService = applicationContext.getBean("bestMovieService", BestMovieService.class);

        //This is a generic fashion
        //Accesses the BestMovieService class (declared above) through the interface Movie (which
        //the BestMovieService class implements)
        Movie bestMovie = bestMovieService.getBestMovie();

        System.out.println("Title : " + bestMovie.getTitle());
        System.out.println("Maturity Rating: " + bestMovie.getMaturityRating());
        System.out.println("Genre: " + bestMovie.getGenre());

        //Closes the container
        applicationContext.close();

    }
}
