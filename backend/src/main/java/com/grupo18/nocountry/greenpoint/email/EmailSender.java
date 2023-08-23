package com.grupo18.nocountry.greenpoint.email;

import com.grupo18.nocountry.greenpoint.auth.token.RegisterToken;
import jakarta.mail.MessagingException;

public interface EmailSender {

    void send(RegisterToken token) throws MessagingException;
}
