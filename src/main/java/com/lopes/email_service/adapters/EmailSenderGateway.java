package com.lopes.email_service.adapters;

public interface EmailSenderGateway {
    void sendEmail(String from, String to, String subject, String body);

}
