package org.weber.siia.channels;

/**
 * Created by wilson on 15/4/23.
 */
public class Email {
    private String recipient;
    private String content;

    public Email(String recipient, String content) {
        this.recipient = recipient;
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
