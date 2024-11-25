package com.lopes.email_service.application;

import com.lopes.email_service.adapters.EmailSenderGateway;
import com.lopes.email_service.core.EmailSenderUserCase;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements EmailSenderUserCase {

    private final EmailSenderGateway emailSenderGateway;

    public EmailSenderService(EmailSenderGateway emailSenderGateway) {
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        this.emailSenderGateway.sendEmail(to, subject, body, "optional-argument");
    }

}
