package com.waquar;

public class AccessRequestTicket extends Ticket implements Escalatable {
    private final String systemName;
    private final AccessLevel accessLevel;
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

    public String getSystemName() {
        return systemName;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
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
