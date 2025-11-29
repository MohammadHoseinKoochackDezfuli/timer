package ir.iraniancyber.khaneshyar.email;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    private final JavaMailSender mailSender;

    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void sendEmail(String to,String text,String subject)
    {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
        try {
            helper.setTo(to);
            helper.setText(text,true);
            helper.setSubject(subject);
            helper.setFrom("mhkd8502@gmail.com");
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        mailSender.send(mimeMessage);
    }
}
