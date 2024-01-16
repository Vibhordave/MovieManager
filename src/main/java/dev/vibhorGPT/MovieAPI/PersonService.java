package dev.vibhorGPT.MovieAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    @Autowired
    private final PersonDao personDao;
    @Autowired
    private final MongoTemplate mongoTemplate;
    @Autowired
    private EmailService emailService;
    @Autowired
    public PersonService(PersonDao personDao, MongoTemplate mongoTemplate) {
        this.personDao = personDao;
        this.mongoTemplate = mongoTemplate;
    }
    public Person createProfile(UUID id, Person person){
        Person person1=new Person(id, person.getEmail(), person.getPassword(), person.getVerificationToken());
        Optional<Person> optionalPerson=personDao.findByEmail(person.getEmail());
        if(optionalPerson.isEmpty()){
            emailService.sendEmailVerification(person1);
            personDao.insert(person1);

            return person1;
        }
        else{
            return null;
        }
    }
    public List<Person> allPerson(){return personDao.findAll();}

    public ResponseEntity<String> verificationCheck(String verId) {
        Optional<Person> person=personDao.findByVerificationToken(verId);
        if(person.isPresent()){
            person.get().setVerified(true);
            return new ResponseEntity<String>("Email Verified!!", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Verification code does not exist", HttpStatus.BAD_REQUEST);
        }
    }
}
