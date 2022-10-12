package com.javaunit3.springmvc;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//Annotate the MovieController class as a controller so that Spring recognizes it as a controller
@Controller
public class MovieController {

    @Autowired
    private BestMovieService bestMovieService;

    @Autowired
    private SessionFactory sessionFactory; //field injection

    //Display the index.html when someone loads the page with the page ("/");
    //Similar to app.get("/") in JS
    @RequestMapping("/")
    public String getIndexPage(){
        return "index";
    }

    @RequestMapping("/bestMovie")
    //Model needs to be imported
    public String getBestMoviePage(Model model)
    {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<MovieEntity> movieEntityList = session.createQuery("from MovieEntity").list();
        movieEntityList.sort(Comparator.comparing(movieEntity -> movieEntity.getVotes().size()));
        MovieEntity movieWithMostVotes = movieEntityList.get(movieEntityList.size() - 1);

        List<String> voterNames = new ArrayList<>();

        for(VoteEntity vote: movieWithMostVotes.getVotes())
            voterNames.add(vote.getVoterName());

        String voterNamesList = String.join(",", voterNames);

        model.addAttribute("bestMovie", movieWithMostVotes.getTitle());
        model.addAttribute("bestMovieVoters", voterNamesList);

        session.getTransaction().commit();

//        Add attribute to model named BestMovie, and use the bestMovieService
//        object to set it to the best movie's title.
//        model.addAttribute("BestMovie", bestMovieService.getBestMovie().getTitle());
        return "bestMovie";
    }

    @RequestMapping("/voteForTheBestMovieForm")
    public String voteForTheBestMovieFormPage(Model model)
    {
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        List<MovieEntity> movieEntityList = session.createQuery("from MovieEntity").list();

        session.getTransaction().commit();

        model.addAttribute("movies", movieEntityList);
        //Returns the webpage voteForTheBestMovie
        return "voteForTheBestMovie";
    }

    @RequestMapping("/voteForTheBestMovie")
    public String voteForTheBestMovie(HttpServletRequest request, Model model)
    {
        String voterName = request.getParameter("voterName");
        String movieID = request.getParameter("movieId");

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        MovieEntity movieEntity = (MovieEntity) session.get(MovieEntity.class, Integer.parseInt(movieID));
        VoteEntity newVote = new VoteEntity();
        newVote.setVoterName(voterName);
        movieEntity.addVote(newVote);
        session.update(movieEntity);

        session.getTransaction().commit();

        return "voteForTheBestMovie";
    }

    @RequestMapping("/addMovieForm")
    public String addMovieForm()
    {
        //returns the addMovie page
        return "addMovie";
    }

    @RequestMapping("/addMovie")
    public String addMovie(HttpServletRequest request)
    {
        //This parameter is from the addMovie page. It finds the names of the
        //requested Strings and added them to the local variables
        String addMovieTitle = request.getParameter("movieTitle");
        String addMaturity = request.getParameter("maturity");
        String addGenre = request.getParameter("genre");

        //Create an instance of the MovieEntity class
        //and set the variables accordingly
        MovieEntity entity = new MovieEntity();
        entity.setTitle(addMovieTitle);
        entity.setGenre(addGenre);
        entity.setMaturityRating(addMaturity);

        //Using the session factory injected, create a seesion object.
        //Begin a session transaction, save the MovieEntity object,
        //Then commit the transaction
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(entity);

        session.getTransaction().commit();

        //returns the addMovie page
        return "addMovie";
    }
}
