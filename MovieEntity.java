package com.javaunit3.springmvc;

import javax.persistence.*;
import java.util.*;

@Entity //defines the class as a Hibernate Entity
@Table(name = "movies") //
public class MovieEntity {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private List<VoteEntity> movieVotes;
    @Id //Identifies it as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //GeneratedValue is used with primary keys
    @Column(name = "movie_id") //Name of the column
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "maturity_rating")
    private String maturityRating;

    @Column(name = "genre")
    private String genre;

    //Setter and getter methods

    public int getId(){
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getMaturityRating(){
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating){
        this.maturityRating = maturityRating;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }


    public List<VoteEntity> getVotes()
    {
        return movieVotes;
    }

    public void setVotes(List<VoteEntity> movieVotes)
    {
        this.movieVotes = movieVotes;
    }

    public void addVote(VoteEntity vote)
    {
        this.movieVotes.add(vote);
    }
}
