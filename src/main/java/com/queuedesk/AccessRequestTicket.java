package com.queuedesk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@ToString(callSuper = true)
@JsonTypeName("access")
public class AccessRequestTicket extends Ticket implements Escalatable {
    private @Getter final String systemName;
    private @Getter final AccessLevel accessLevel;
    private boolean escalated = false;
    private int escalationLevel = 0;


    public AccessRequestTicket(String title, String description, String requester, Priority priority, String systemName, AccessLevel accessLevel) {
        super(title, description, requester, priority);
        this.systemName = systemName;
        this.accessLevel = accessLevel;
    }

    public AccessRequestTicket(String title, String requester, String systemName, AccessLevel accessLevel) {
        super(title, requester);
        this.systemName = systemName;
        this.accessLevel = accessLevel;
    }

    @JsonCreator
    public AccessRequestTicket(@JsonProperty("id") int id,
                               @JsonProperty("title") String title,
                               @JsonProperty("description") String description,
                               @JsonProperty("requester") String requester,
                               @JsonProperty("priority") Priority priority,
                               @JsonProperty("status") Status status,
                               @JsonProperty("createdAt") LocalDateTime createdAt,
                               @JsonProperty("assignee") String assignee,
                               @JsonProperty("tags") Set<String> tags,
                               @JsonProperty("systemName") String systemName,
                               @JsonProperty("accessLevel") AccessLevel accessLevel,
                               @JsonProperty("escalated") boolean escalated,
                               @JsonProperty("escalationLevel") int escalationLevel) {
        super(id, title, description, requester, priority, status, createdAt, assignee, tags);
        this.systemName = systemName;
        this.accessLevel = accessLevel;
        this.escalated = escalated;
        this.escalationLevel = escalationLevel;
    }

    @Override
    public double estimateEffortHours() {
        return 5;
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
