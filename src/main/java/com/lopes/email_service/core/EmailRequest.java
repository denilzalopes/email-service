package com.lopes.email_service.core;



public record EmailRequest(String to, String subject, String body) {

}
