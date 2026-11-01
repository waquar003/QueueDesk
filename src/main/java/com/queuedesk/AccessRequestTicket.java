package com.queuedesk;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.ToString;

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
    public int escalationLevel() {
        return this.escalationLevel;
    }
}
