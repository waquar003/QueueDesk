package com.queuedesk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.queuedesk.enums.Priority;
import com.queuedesk.enums.Severity;
import com.queuedesk.enums.Status;
import com.queuedesk.interfaces.Escalatable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@JsonTypeName("bug")
@ToString(callSuper = true)
public class BugTicket extends Ticket implements Escalatable {
    private @Getter @Setter Severity severity;
    private @Getter @Setter String stepsToReproduce;
    private boolean escalated = false;
    private int escalationLevel = 0;

    public BugTicket(String title, String description, String requester, Priority priority, Severity severity, String stepsToReproduce) {
        super(title, description, requester, priority);
        this.severity = severity;
        this.stepsToReproduce = stepsToReproduce;
    }

    public BugTicket(String title, String requester) {
        this(title, "", requester, Priority.LOW, Severity.LOW, "");
    }

    @JsonCreator
    public BugTicket(@JsonProperty("id") int id,
                     @JsonProperty("title") String title,
                     @JsonProperty("description") String description,
                     @JsonProperty("requester") String requester,
                     @JsonProperty("priority") Priority priority,
                     @JsonProperty("status") Status status,
                     @JsonProperty("createdAt") LocalDateTime createdAt,
                     @JsonProperty("assignee") String assignee,
                     @JsonProperty("tags") Set<String> tags,
                     @JsonProperty("severity") Severity severity,
                     @JsonProperty("stepsToReproduce") String stepsToReproduce,
                     @JsonProperty("escalated") boolean escalated,
                     @JsonProperty("escalationLevel") int escalationLevel) {
        super(id, title, description, requester, priority, status, createdAt, assignee, tags);
        this.severity = severity;
        this.stepsToReproduce = stepsToReproduce;
        this.escalated = escalated;
        this.escalationLevel = escalationLevel;
    }

    @Override
    public double estimateEffortHours() {
        switch (this.severity) {
            case LOW -> {
                return 2;
            }
            case MEDIUM -> {
                return 6;
            }
            case HIGH -> {
                return 15;
            }
            case CRITICAL -> {
                return 40;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public void escalate() {
        this.escalated = true;
        this.escalationLevel++;
    }

    @Override
    public boolean isEscalated() {
        return this.escalated;
    }

    @Override
    @JsonProperty("escalationLevel")
    public int escalationLevel() {
        return this.escalationLevel;
    }
}
