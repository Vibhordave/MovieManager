package dev.vibhorGPT.MovieAPI;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
    Optional<Movie> findByImdbId(String imdbId);// Basically find(CLASS_NAME)by(Field Name) ya fir findBy(FIELD NAME)
}
