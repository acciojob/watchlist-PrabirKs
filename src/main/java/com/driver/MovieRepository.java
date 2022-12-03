package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@Repository
@Component
public class MovieRepository {
    HashMap<String,Movie> movieDB ;
    HashMap<String,Director> directorDB ;
    HashMap<String, ArrayList<String>> directorMoviePairDB ;
    //initialising the storage for each class
    public MovieRepository() {
        this.movieDB = new HashMap<String,Movie>();
        this.directorDB = new HashMap<String,Director>();
        this.directorMoviePairDB = new HashMap<String,ArrayList<String>>() ;
    }
    //storing the Movie in databse
    public void storeMovieInDB(Movie movie){
        movieDB.put(movie.getName(),movie) ;
    }
    //storing the director names in database
    public void storeDirectorInDB(Director director){
        directorDB.put(director.getName(),director) ;
    }
    //save movie-director pair
    public void storeMovieDirectorPairInDB(String movie,String director){
        //checking that whether that movie and director present or not
        if(movieDB.containsKey(movie) && directorDB.containsKey(director)){
            ArrayList<String> allMovies = new ArrayList<>() ;
            //check whether that director present in the movie-directorDB
            if(directorMoviePairDB.containsKey(director)){
                allMovies = directorMoviePairDB.get(director) ;
            }
            allMovies.add(movie) ;
            //adding the director and movie pair in the pairDB
            directorMoviePairDB.put(director,allMovies) ;
        }
    }

    //finding movie and director

    public Movie findMovie(String movieName){
        if(movieDB.containsKey(movieName))
          return movieDB.get(movieName) ;
        else
          return null ;
    }

    public Director findDirector(String directorName){
        if(directorDB.containsKey(directorName))
            return directorDB.get(directorName) ;
        else
            return null ;
    }

    //get list of movies by director name
    public ArrayList<String> findMoviesByDirector(String directorName){
        ArrayList<String> listOfAllMovies = new ArrayList<>() ;
        if(directorDB.containsKey(directorName)){
            listOfAllMovies = directorMoviePairDB.get(directorName) ;  //fetching movie list from the pair db
        }
        return  listOfAllMovies ;
    }
   //get list of ALL movie added
    public ArrayList<String> getAllMovies(){
        ArrayList<String> movies = new ArrayList<>(movieDB.keySet()) ;
        return movies ;
    }
   //delete director and its movies from the database
    public void deleteDirector(String name){
        directorDB.remove(name) ;
        ArrayList<String> moviesOfDirector = new ArrayList<>() ;
        moviesOfDirector = directorMoviePairDB.get(name) ;
        for(String movie : moviesOfDirector){
            movieDB.remove(movie) ;
        }
        directorMoviePairDB.remove(name) ;
    }
    //delete all directors
    public void deleteAllDirector() {
        HashSet<String> movies = new HashSet<>() ;
        //storing all the movies by all directors
        for (String directors : directorMoviePairDB.keySet()){
            movies.addAll(directorMoviePairDB.get(directors)) ;
        }
        //deleting the movies from the movie database
        for(String movie : movies){
            movieDB.remove(movie) ;
        }
        //clearing the director map and the pair database
        directorMoviePairDB.clear();
        directorDB.clear();
    }
}
