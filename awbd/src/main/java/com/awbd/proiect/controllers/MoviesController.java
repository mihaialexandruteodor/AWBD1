package com.awbd.proiect.controllers;

import com.awbd.proiect.domain.Category;
import com.awbd.proiect.domain.Movie;
import com.awbd.proiect.services.CategoryService;
import com.awbd.proiect.services.ImageService;
import com.awbd.proiect.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class MoviesController {

    @Autowired
    MovieService movieService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ImageService imageService;

    @Autowired
    public MoviesController(MovieService movieService) {
        this.movieService = movieService;
    }

    // SHOP REDIRECT AND CONTROLLER


    @RequestMapping("/shop")
    public ModelAndView shopList(){
        ModelAndView modelAndView = new ModelAndView("movies");
        List<Movie> movies = movieService.findAll();
        modelAndView.addObject("movies",movies);
        return modelAndView;
    }

    // ABOUT REDIRECT AND CONTROLLER


    @RequestMapping(method = RequestMethod.GET, value = "/about")
    public String index() {
        return "about.html";
    }

    // FROM THE LAB BELOW


    @RequestMapping("/movie/list")
    public ModelAndView moviesList(){
        ModelAndView modelAndView = new ModelAndView("movies");
        List<Movie> movies = movieService.findAll();
        modelAndView.addObject("movies",movies);
        return modelAndView;
    }

    @GetMapping("/movie/info/{id}")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("movie",
                movieService.findById(Long.valueOf(id)));
        return "info";
    }

    @RequestMapping("/movie/delete/{id}")
    public String deleteById(@PathVariable String id){
        movieService.deleteById(Long.valueOf(id));
        return "redirect:/movie/list";
    }

    @RequestMapping("/movie/new")
    public String newProduct(Model model) {
        model.addAttribute("movie", new Movie());
        List<Category> categoriesAll = categoryService.findAll();

        model.addAttribute("categoriesAll", categoriesAll );
        return "movieform";
    }

    @PostMapping("/movie")
    public String saveOrUpdate(@ModelAttribute Movie movie,
                               @RequestParam("imagefile") MultipartFile file){
        Movie savedMovie = movieService.save(movie);
        imageService.saveImageFile(Long.valueOf(savedMovie.getId()), file);
        //return "redirect:/movie/info/" + savedProduct.getId();
        return "redirect:/movie/list" ;
    }

}
