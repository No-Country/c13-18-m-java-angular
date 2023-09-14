package com.grupo18.nocountry.greenpoint.email;

import com.grupo18.nocountry.greenpoint.auth.PasswordReset.PasswordResetToken;
import com.grupo18.nocountry.greenpoint.auth.token.RegisterToken;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements EmailSender {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String email;


    @Override
    public void send(RegisterToken token) throws MessagingException {
        String name = token.getUser().getFirstname();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mime = new MimeMessageHelper(message, true, "UTF-8");

        mime.setTo(token.getUser().getUsername());
        mime.setFrom(email);
        mime.setSubject("Bienvenido a Green Point - Activación de usuario");
        mime.setText(this.createMessage(token.getToken().toString(), name), true);
        mailSender.send(message);
    }


    private String createMessage(String token, String name) {
        String email = "<!doctype html>\n" +
                "<html lang=\"en-US\">\n" +
                "<head>\n" +
                "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
                "    <title>Reset Password Email Template</title>\n" +
                "    <meta name=\"description\" content=\"Reset Password Email Template.\">\n" +
                "    <style type=\"text/css\">\n" +
                "        a:hover {text-decoration: underline !important;}\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
                "    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
                "        style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "                <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\n" +
                "                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>\n" +
                "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n" +
                "                                <tr>\n" +
                "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td style=\"padding:0 35px;\">\n" +
                "                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">Hola {{name}}</h1>\n" +
                "                                        <span\n" +
                "                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\n" +
                "                                        <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\n" +
                "                                            Verificá tu email y comenzá a ganar puntos con Green Point!\n" +
                "                                        </p>\n" +
                "                                        <a href=\"http://greenpoint.zanity.net/token/confirm-mail/{{token}}\"\n" +
                "                                            style=\"background:#20e277;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">Confirmar email</a>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"text-align:center;\">\n" +
                "                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>Green Point</strong></p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</body>\n" +
                "</html>";

        return email.replace("{{token}}", token).replace("{{name}}", name);
    }


    public void sendPasswordResetEmail(PasswordResetToken token) throws MessagingException {
        String resetUrl = "http://greenpoint.zanity.net/token/confirm-reset?token=" + token.getToken();
        String name = token.getUser().getFirstname();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mime = new MimeMessageHelper(message, true, "UTF-8");

        mime.setTo(token.getUser().getUsername());
        mime.setFrom(email);
        mime.setSubject("Reinicio de contraseña - Green Point");
        mime.setText(this.createPasswordResetMessage(resetUrl, name), true);
        mailSender.send(message);
    }

    private String createPasswordResetMessage(String resetUrl, String name) {
        String emailContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Password Reset - Green Point</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            background-color: #f2f3f8;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 670px;\n" +
                "            margin: 0 auto;\n" +
                "            background: #fff;\n" +
                "            border-radius: 3px;\n" +
                "            text-align: center;\n" +
                "            box-shadow: 0 6px 18px 0 rgba(0, 0, 0, 0.06);\n" +
                "        }\n" +
                "        .header {\n" +
                "            height: 80px;\n" +
                "        }\n" +
                "        .spacer-20 {\n" +
                "            height: 20px;\n" +
                "        }\n" +
                "        .content {\n" +
                "            padding: 0 35px;\n" +
                "        }\n" +
                "        .title {\n" +
                "            color: #1e1e2d;\n" +
                "            font-weight: 500;\n" +
                "            margin: 0;\n" +
                "            font-size: 32px;\n" +
                "            font-family: 'Rubik', sans-serif;\n" +
                "        }\n" +
                "        .divider {\n" +
                "            display: inline-block;\n" +
                "            vertical-align: middle;\n" +
                "            margin: 29px 0 26px;\n" +
                "            border-bottom: 1px solid #cecece;\n" +
                "            width: 100px;\n" +
                "        }\n" +
                "        .message {\n" +
                "            color: #455056;\n" +
                "            font-size: 15px;\n" +
                "            line-height: 24px;\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "        .button {\n" +
                "            background: #20e277;\n" +
                "            text-decoration: none !important;\n" +
                "            font-weight: 500;\n" +
                "            margin-top: 35px;\n" +
                "            color: #fff;\n" +
                "            text-transform: uppercase;\n" +
                "            font-size: 14px;\n" +
                "            padding: 10px 24px;\n" +
                "            display: inline-block;\n" +
                "            border-radius: 50px;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .footer-text {\n" +
                "            font-size: 14px;\n" +
                "            color: rgba(69, 80, 86, 0.7411764705882353);\n" +
                "            line-height: 18px;\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "        .footer-text strong {\n" +
                "            color: #000;\n" +
                "        }\n" +
                "        .footer-space {\n" +
                "            height: 80px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\"></div>\n" +
                "        <div class=\"spacer-20\"></div>\n" +
                "        <div class=\"content\">\n" +
                "            <h1 class=\"title\">Hola " + name + "!</h1>\n" +
                "            <div class=\"divider\"></div>\n" +
                "            <p class=\"message\">\n" +
                "                Recibimos una solicitud para restablecer tu contraseña de Green Point. Si no fuiste vos, ignorá este mensaje.\n" +
                "            </p>\n" +
                "            <a href=\"" + resetUrl + "\" class=\"button\">Restablecer contraseña</a>\n" +
                "        </div>\n" +
                "        <div class=\"spacer-20\"></div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p class=\"footer-text\">&copy; <strong>Green Point</strong></p>\n" +
                "        </div>\n" +
                "        <div class=\"footer-space\"></div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        return emailContent;
    }
}
