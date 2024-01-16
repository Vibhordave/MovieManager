package dev.vibhorGPT.MovieAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "spring.mail")
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailVerification(Person person){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(person.getEmail());
        simpleMailMessage.setSubject("Email Verification for the Gold Website");
        simpleMailMessage.setText("Click the link below to verify your email:\n\n" +
                "http://localhost:3000/api/v1/register/verify/" + person.getVerificationToken());

        javaMailSender.send(simpleMailMessage);
    }
}
