package com.lopes.email_service.infra.ses;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.lopes.email_service.adapters.EmailSenderGateway;
import com.lopes.email_service.core.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    public SesEmailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    @Override
    public void sendEmail(String from, String to, String subject, String body) {
        try {
            // Crée le contenu du message
            Message message = new Message()
                    .withSubject(new Content().withData(subject)) // Ajoute le sujet
                    .withBody(new Body().withText(new Content().withData(body))); // Ajoute le corps du message

            // Configure la requête d'envoi d'email
            SendEmailRequest request = new SendEmailRequest()
                    .withSource(from) // L'adresse email de l'expéditeur
                    .withDestination(new Destination().withToAddresses(to)) // Destinataire
                    .withMessage(message); // Message complet

            // Envoie l'email via le service Amazon SES
            amazonSimpleEmailService.sendEmail(request);
        } catch (AmazonServiceException exception) {
            // Gérer les exceptions liées à Amazon SES
            throw new EmailServiceException("Failure while sending email: " + exception.getMessage(), exception);
        }
    }
}
