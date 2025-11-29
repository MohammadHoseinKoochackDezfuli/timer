package ir.iraniancyber.khaneshyar.controller;

import ir.iraniancyber.khaneshyar.email.EmailSender;
import ir.iraniancyber.khaneshyar.model.User;
import ir.iraniancyber.khaneshyar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/sendEmail")
public class EmailController {
    @Autowired
    UserRepository userRepository;
    private final EmailSender emailSender;

    public EmailController(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String to,String subject,String text)
    {
        emailSender.sendEmail(to,text,subject);
    }

    @GetMapping("/sendNumber")
    public ResponseEntity<?> sendNumber(@RequestParam String email)
    {
        int randomNumber = new Random().nextInt(9000)+1000;
        userRepository.save(new User(0,email,randomNumber,false));
        sendEmail(email,"Email confirmation","<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body style=\"background-color: black;color: white;\">\n" +
                "    <p>Dear user, enter this number to verify your email.</p>\n" +
                "    <center>\n" +
                "        <div style=\"width: 40%;min-width: 300px;border: 5px double rgb(50,180,255);padding: 20px;border-radius: 30px;\">"+randomNumber+"</div>\n" +
                "    </center>\n" +
                "    <p>Thank you for using SendEmail.</p>\n" +
                "    <p><span style=\"color: yellow;\">&#9888;</span> Do not share this number with anyone.</p>\n" +
                "</body>\n" +
                "</html>");
        return ResponseEntity.ok().build();
    }
    @GetMapping("/checkNumber")
    public boolean checkNumber(@RequestParam String email,@RequestParam int number)
    {
        Optional<User> userOptional=userRepository.findByMail(email);
        if(userOptional.isEmpty())
        {
            return false;
        }
        User user=userOptional.get();
        if(user.getCode()==number)
        {
            user.setOk(true);
            userRepository.save(user);
            return true;
        }
        else
        {
            return false;
        }
    }
}
