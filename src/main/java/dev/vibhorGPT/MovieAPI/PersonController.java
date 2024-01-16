package dev.vibhorGPT.MovieAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("api/v1/register")
@RestController
@CrossOrigin(origins = "*")
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    public ResponseEntity<Person> createProfile(@RequestBody Map<String,String> payload){
        UUID id=UUID.randomUUID();
        String verificationToken = UUID.randomUUID().toString();
        Person person=personService.createProfile(id,new Person(id,payload.get("email"),payload.get("password"),verificationToken));
        if(person != null){
            return new ResponseEntity<Person>(person, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<Person>((Person) null,HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/verify/{verId}")
    public ResponseEntity<String> verificationCheck(@PathVariable("verId") String verId){
        return personService.verificationCheck(verId);
    }
    @GetMapping
    public ResponseEntity<List<Person>> showAllPeople(){return new ResponseEntity<List<Person>>(personService.allPerson(),HttpStatus.OK);}

}
