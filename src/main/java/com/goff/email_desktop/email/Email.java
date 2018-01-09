package com.goff.email_desktop.email;

public class Email {

    private String destination;
    private String body;
    private String attachment;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(destination).append(" (").append(attachment).append(')');
        return sb.toString();
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
}
