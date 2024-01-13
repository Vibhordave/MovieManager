package dev.vibhorGPT.MovieAPI;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<List<Movie>>(movieService.allMovies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Movie>> getMovieById(@PathVariable("id")ObjectId id){
        return new ResponseEntity<Optional<Movie>>(movieService.movieById(id),HttpStatus.OK);
    }
    // if we want to find by Imdbid we will use automatic querie , see from MovieRepository
    @GetMapping("/Imdb/{imdbId}")
    public ResponseEntity<Optional<Movie>> getMovieByImdbId(@PathVariable("imdbId")String imdbId){
        return new ResponseEntity<Optional<Movie>>(movieService.movieByImdbId(imdbId),HttpStatus.OK);
    }
}
