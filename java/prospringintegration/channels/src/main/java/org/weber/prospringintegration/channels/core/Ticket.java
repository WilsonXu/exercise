package org.weber.prospringintegration.channels.core;

import java.util.Calendar;

/**
 * Created by wilson on 15/5/5.
 */
public class Ticket {
    public enum Priority {
        low,
        medium,
        high,
        emergency
    }

    private long ticketId;
    private Calendar issueDateTime;
    private String description;
    private Priority priority;

    public Ticket() {
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public Calendar getIssueDateTime() {
        return issueDateTime;
    }

    public void setIssueDateTime(Calendar issueDateTime) {
        this.issueDateTime = issueDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return String.format("Ticket# %d: [%s] %s", ticketId, priority, description);
    }
}
