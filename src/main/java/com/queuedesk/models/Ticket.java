package com.queuedesk.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.queuedesk.enums.Priority;
import com.queuedesk.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
     @JsonSubTypes.Type(value = BugTicket.class, name = "bug"),
     @JsonSubTypes.Type(value = FeatureRequestTicket.class, name = "feature"),
     @JsonSubTypes.Type(value = AccessRequestTicket.class, name = "access")
})
public abstract class Ticket implements Comparable<Ticket> {
    private @Getter final int id;
    private @Getter final String title;
    private @Getter String description;
    private @Getter final String requester;
    private @Setter @Getter Priority priority;
    private @Setter @Getter Status status;
    private @Getter final LocalDateTime createdAt;
    private @Setter @Getter String assignee = null;
    private @Getter Set<String> tags;

    private static int nextId = 0;

    protected Ticket(int id, String title, String description, String requester,
                     Priority priority, Status status, LocalDateTime createdAt,
                     String assignee, Set<String> tags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.requester = requester;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.assignee = assignee;
        this.tags = (tags == null) ? new HashSet<>() : new HashSet<>(tags);
        nextId = Math.max(nextId, id);
    }

    public Ticket(String title, String description, String requester, Priority priority) {
        this(++nextId, title, description, requester, priority, Status.OPEN, LocalDateTime.now(), null, null);
    }

    public Ticket(String title, String requester) {
        this(title, "", requester, Priority.LOW);
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isBlank()) {
            this.description = "No description provided.";
            return;
        }
        String trimmed = description.trim();
        this.description = trimmed.length() > 255 ? trimmed.substring(0, 255) : trimmed;
    }

    public long ageInHours() {
        return ChronoUnit.HOURS.between(createdAt, LocalDateTime.now());
    }

    public static int totalCreated() {
        return nextId;
    }

    public abstract double estimateEffortHours();

    public void addTag(String tag) {
        tags.add(tag);
    }

    @Override
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
