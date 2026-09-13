package com.waquar;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class Ticket {
    private final int id;
    private final String title;
    private String description;
    private final String requester;
    private Priority priority;
    private Status status;
    private final LocalDateTime createdAt;

    private static int nextId = 0;

    public Ticket(String title, String description, String requester, Priority priority) {
        this.id = ++nextId;
        this.title = title;
        this.description = description;
        this.requester = requester;
        this.priority = priority;
        this.status = Status.OPEN;
        this.createdAt = LocalDateTime.now();
    }

    public Ticket(String title, String requester) {
        this(title, "", requester, Priority.LOW);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRequester() {
        return requester;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public long ageInHours() {
        return ChronoUnit.HOURS.between(createdAt, LocalDateTime.now());
    }

    public static int totalCreated() {
        return nextId;
    }

    public abstract double estimateEffortHours();
}
