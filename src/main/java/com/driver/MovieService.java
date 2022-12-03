package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Component
public class MovieService {
    @Autowired
    MovieRepository movieRepository ;
    //adding movie in the DB
    public void addMovieInDB(Movie movie){
        movieRepository.storeMovieInDB(movie);
    }
    //adding directors into DB
    public  void addDirectorInDB(Director director){
        movieRepository.storeDirectorInDB(director);
    }
    //add movie director pair in DB
    public void addMovieDirectorPair(String movieName,String directorName){
        movieRepository.storeMovieDirectorPairInDB(movieName,directorName);
    }
    //get the movie by their name
    public Movie findMovie(String movieName){
        return movieRepository.findMovie(movieName) ;
    }
    //get the director by its name
    public Director findDirector(String directorName){
        return movieRepository.findDirector(directorName) ;
    }
    //get all the movies of a director
    public ArrayList<String> findMoviesOfDirector(String directorName){
        return movieRepository.findMoviesByDirector(directorName) ;
    }
    //get All the movies in the database
    public ArrayList<String> findAllMovies(){
        return movieRepository.getAllMovies() ;
    }
    //deleting director and his nmovies
    public void deleteDirector(String director){
        movieRepository.deleteDirector(director);
    }
    //deleting all the directors from all database
    public void deleteAllDirector(){
        movieRepository.deleteAllDirector();
    }


}
