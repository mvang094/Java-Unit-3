package com.javaunit3.springmvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//Identifies the class as a Spring Component
@Component
public class BestMovieService
{
    //Define a private Movie property and use Spring annotations so that it is Autowired
    //Use field injection to inject a Movie object into the BestMovieService class
    //This can also be done by using a constructor (See constructor) and setter injection (See setMovie)
    //@Autowired - allows field injections
    private Movie movie;

    @Autowired
    //@Qualifier injects preferred bean into the constructor. like when parsing
    //bean id must match the one generated by Java i.e. batman = batmanMovie (notice no capital B)
    public BestMovieService(@Qualifier("titanicMovie") Movie movie)
    {
        this.movie = movie;
    }

    public Movie getBestMovie()
    {
        return movie;
    }

//    @Autowired
//    public void setMovie(Movie movie)
//    {
//        this.movie = movie;
//    }
}