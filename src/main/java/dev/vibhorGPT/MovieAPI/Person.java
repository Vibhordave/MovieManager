package dev.vibhorGPT.MovieAPI;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "people")
public class Person {
    private final UUID id;

    private final String email;
    private final String password;
    private boolean verified = false;
    private final String verificationToken;

    public Person(UUID id, String email, String password, String verificationToken) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.verificationToken = verificationToken;
    }

}
