package com.javaunit3.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class MovieController {

    @Autowired
    private BestMovieService bestMovieService;

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
        //Add attribute to model named BestMovie, and use the bestMovieService
//        object to set it to the best movie's title.
        model.addAttribute("BestMovie", bestMovieService.getBestMovie().getTitle());
        return "bestMovie";
    }
}
