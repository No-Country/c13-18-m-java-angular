package com.grupo18.nocountry.greenpoint.email;

import com.grupo18.nocountry.greenpoint.auth.token.RegisterToken;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class EmailService implements EmailSender {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String email;


    @Override
    public void send(RegisterToken token) throws MessagingException {
        String clientUrl = "htpp://localhost:4200/";
        String name = token.getUser().getFirstname();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mime = new MimeMessageHelper(message, true, "UTF-8");

        mime.setTo(token.getUser().getUsername());
        mime.setFrom(email);
        mime.setSubject("Bienvenido a Green Point - Activación de usuario");
        mime.setText(this.createMessage(token.getToken().toString(), name), true);
        mailSender.send(message);
    }


    private String createMessage(String token,String name)  {
        try {
            Resource resource = new ClassPathResource("templates/email_template.html");
            File file = resource.getFile();
            String templateContent = Files.readString(file.toPath());

            return templateContent.replace("{{token}}", token).replace("{{name}}", name);
        } catch (IOException e) {

            e.printStackTrace();
            return "";
        }
    }
}
