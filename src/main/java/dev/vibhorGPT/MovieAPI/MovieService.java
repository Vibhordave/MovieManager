package dev.vibhorGPT.MovieAPI;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> allMovies(){
        return movieRepository.findAll();
    }
    public Optional<Movie> movieById(ObjectId id){
        return movieRepository.findById(id);
    }
    public Optional<Movie> movieByImdbId(String imdbId){
        return movieRepository.findByImdbId(imdbId);
    }
}
