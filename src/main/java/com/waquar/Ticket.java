package com.waquar;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

public abstract class Ticket implements Comparable<Ticket> {
    private final int id;
    private final String title;
    private String description;
    private final String requester;
    private Priority priority;
    private Status status;
    private final LocalDateTime createdAt;
    private String assignee = null;
    private Set<String> tags;

    private static int nextId = 0;

    public Ticket(String title, String description, String requester, Priority priority) {
        this.id = ++nextId;
        this.title = title;
        this.description = description;
        this.requester = requester;
        this.priority = priority;
        this.status = Status.OPEN;
        this.createdAt = LocalDateTime.now();
        this.tags = new HashSet<String>();
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

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", requester='" + requester + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }


    public Set<String> getTags() {
        return tags;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public int compareTo(Ticket other) {
        int priorityCompare = other.priority.compareTo(this.priority);
        if (priorityCompare != 0) return priorityCompare;

        return this.createdAt.compareTo(other.createdAt);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ticket other)) return false;
        return this.id == other.id;
    }
}
