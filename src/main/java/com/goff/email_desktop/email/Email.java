package com.goff.email_desktop.email;

import java.util.Enumeration;
import java.util.regex.Pattern;

import com.goff.email_desktop.param.Parameter;

public class Email {

    private String destination;
    private String body;
    private String attachment;
    private String name;
    private boolean sended;
    private boolean withSendingError;

    public String loadDefaultBody() {
        final String defaultBody = EmailDefaultBody.read();
        return defaultBody.replaceAll(Pattern.quote(Parameter.NAME), name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(destination).append(" (").append(attachment).append(')');
        if (withSendingError) {
            sb.append(" - Error");
        }
        if (sended) {
            sb.append(" - Sended");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        final Email email = (Email) obj;
        return name.equals(email.name) && destination.equals(email.destination)
                && attachment.equals(email.attachment) && body.equals(email.body);
    }

    public boolean missingRequiredField() {
        return name == null || body == null || destination == null || attachment == null;
    }

    public boolean repeated(final Enumeration<Email> emailList) {
        while (emailList.hasMoreElements()) {
            final Email email = emailList.nextElement();
            if (destination.equals(email.getDestination())) {
                return true;
            }
        }
        return false;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(final String attachment) {
        this.attachment = attachment;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isSended() {
        return sended;
    }

    public void setSended(final boolean sended) {
        this.sended = sended;
    }

    public boolean isWithSendingError() {
        return withSendingError;
    }

    public void setWithSendingError(final boolean withSendingError) {
        this.withSendingError = withSendingError;
    }

}
