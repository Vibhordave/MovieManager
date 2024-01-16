package dev.vibhorGPT.MovieAPI;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonDao extends MongoRepository<Person, UUID> {
    Optional<Person> findByEmail(String email);
    Optional<Person> findByVerificationToken(String verificationToken);
}
