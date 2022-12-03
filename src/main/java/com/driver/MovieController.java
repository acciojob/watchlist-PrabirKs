package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    MovieService movieService ;

    // it will contain all the end point API

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovieInDB(movie);
        return new ResponseEntity<>("Successful", HttpStatus.CREATED) ;
    }
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirectorInDB(director);
        return new ResponseEntity<>("Successful",HttpStatus.CREATED) ;
    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movie,@RequestParam String director){
        movieService.addMovieDirectorPair(movie,director);
        return new ResponseEntity<>("Successful",HttpStatus.CREATED) ;
    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
         Movie movie = movieService.findMovie(name) ;
         return new ResponseEntity<>(movie,HttpStatus.CREATED) ;
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){
        Director director = movieService.findDirector(name) ;
        return new ResponseEntity<>(director,HttpStatus.CREATED) ;
    }
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<ArrayList<String>> getMoviesByDirectorName(@PathVariable String director){
        ArrayList<String> moviesOfDirector = new ArrayList<>() ;
        moviesOfDirector = movieService.findMoviesOfDirector(director) ;
        return new ResponseEntity<>(moviesOfDirector,HttpStatus.CREATED) ;
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity<ArrayList<String>> findAllMovies(){
        ArrayList<String> Movies = movieService.findAllMovies() ;
        return  new ResponseEntity<>(Movies,HttpStatus.CREATED) ;
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String director){
        movieService.deleteDirector(director);
        return new ResponseEntity<>(director + " removed",HttpStatus.CREATED) ;
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirector();
        return new ResponseEntity<>("ALl director deleted",HttpStatus.CREATED) ;
    }
}
